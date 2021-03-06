//  Copyright 2012 Foursquare Labs Inc. All Rights Reserved
package com.foursquare.twofishes

import com.foursquare.geo.shapes.ShapefileS2Util
import com.foursquare.twofishes.Identity._
import com.foursquare.twofishes.util.{GeoTools, GeometryUtils, StoredFeatureId, TwofishesLogger}
import com.foursquare.twofishes.util.Lists.Implicits._
import com.google.common.geometry.S2CellId
import com.twitter.ostrich.stats.Stats
import com.twitter.util.Duration
import com.vividsolutions.jts.geom.{Coordinate, Geometry, GeometryFactory, Point => JTSPoint}
import com.vividsolutions.jts.io.{WKBReader, WKTWriter}
import com.vividsolutions.jts.util.GeometricShapeFactory
import com.weiglewilczek.slf4s.Logging
import org.apache.thrift.TBaseHelper
import org.bson.types.ObjectId
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import scalaj.collection.Implicits._


class ReverseGeocodeParseOrdering extends Ordering[Parse[Sorted]] {
  def compare(a: Parse[Sorted], b: Parse[Sorted]): Int = {
    val comparisonOpt = for {
      aFeatureMatch <- a.headOption
      bFeatureMatch <- b.headOption
    } yield {
      val aServingFeature = aFeatureMatch.fmatch
      val bServingFeature = bFeatureMatch.fmatch
      val aWoeTypeOrder = YahooWoeTypes.getOrdering(
        aServingFeature.feature.woeTypeOption.getOrElse(YahooWoeType.UNKNOWN))
      val bWoeTypeOrder = YahooWoeTypes.getOrdering(
        bServingFeature.feature.woeTypeOption.getOrElse(YahooWoeType.UNKNOWN))
      if (aWoeTypeOrder != bWoeTypeOrder) {
         aWoeTypeOrder - bWoeTypeOrder
      } else {
        bServingFeature.scoringFeatures.boost -
          aServingFeature.scoringFeatures.boost
      }
    }

    comparisonOpt.getOrElse(0)
  }
}

trait TimeResponseHelper {
  def timeResponse(ostrichKey: String)(f: GeocodeResponse) = {
    val (rv, duration) = Duration.inNanoseconds(f)
    Stats.addMetric(ostrichKey + "_usec", duration.inMicroseconds.toInt)
    Stats.addMetric(ostrichKey + "_msec", duration.inMilliseconds.toInt)
    if (rv.interpretations.size > 0) {
      Stats.addMetric(ostrichKey + "_with_results_usec", duration.inMicroseconds.toInt)
      Stats.addMetric(ostrichKey + "_with_results_msec", duration.inMilliseconds.toInt)
    }
    rv
  }
}

case class CellGeometryWrapper(cell: CellGeometry, cellid: Long) {
  def wkbGeometryOption = cell.wkbGeometryOption
  def woeTypeOption = cell.woeTypeOption
  def full = cell.full
  def longIdOption = cell.longIdOption
  lazy val s2cellid = new S2CellId(cellid)
  lazy val geomOption: Option[Geometry] = {
    val wkbReader = new WKBReader()
    if (full) {
      Some(ShapefileS2Util.fullGeometryForCell(s2cellid))
    } else {
      wkbGeometryOption.map(wkbGeometry => wkbReader.read(TBaseHelper.byteBufferToByteArray(wkbGeometry)))
    }
  }
}

class ReverseGeocoderHelperImpl(
  store: GeocodeStorageReadService,
  req: CommonGeocodeRequestParams,
  queryLogger: MemoryLogger
) extends GeocoderImplTypes with TimeResponseHelper with BulkImplHelpers with Logging {
  def computeOverlapArea(
    featureGeometries: Seq[CellGeometryWrapper],
    requestGeometry: Geometry
  ): Double = {
    // there's still some redundant work here -- we've already determined if our request geom doesn't touch
    // the geometry in some of the cells when we computed findMatches.
    val areas = for {
      cellGeometry <- featureGeometries
      geom <- cellGeometry.geomOption
    } yield {
      try {
        geom.intersection(requestGeometry).getArea()
      } catch {
        case e: Throwable =>
          Stats.addMetric("intersection_exception", 1)
          logger.error("failed to calculate intersection: %s x %s".format(geom, requestGeometry), e)
          0.0
      }
    }
    areas.sum
  }

  def responseIncludes(include: ResponseIncludes): Boolean =
    GeocodeRequestUtils.responseIncludes(req, include)

  def s2CoverGeometry(geom: Geometry): Seq[Long] = {
    geom match {
      case p: JTSPoint =>
        val levels = getAllLevels()
        queryLogger.ifDebug("doing point revgeo on %s at levels %s", p, levels)
        levels.map(level =>
          GeometryUtils.getS2CellIdForLevel(p.getCoordinate.y, p.getCoordinate.x, level).id()
        )
      case g =>
        val cellids = queryLogger.logDuration("s2_cover_time", "s2_cover_time") {
          GeometryUtils.coverAtAllLevels(
            geom,
            store.getMinS2Level,
            store.getMaxS2Level,
            Some(store.getLevelMod)
          ).map(_.id())
        }
        Stats.addMetric("num_geom_cells", cellids.size)
        cellids
    }
  }

  def findMatches(
    otherGeom: Geometry,
    cellGeometries: Seq[CellGeometryWrapper]
  ): Seq[StoredFeatureId] = {
    if (req.debug > 0) {
      queryLogger.ifDebug("had %d candidates", cellGeometries.size)
      // queryLogger.ifDebug("s2 cells: %s", cellids)
    }

    val matches = new ListBuffer[StoredFeatureId]()

    for {
      cellGeometry <- cellGeometries
      if (req.woeRestrict.isEmpty || cellGeometry.woeTypeOption.exists(req.woeRestrict.has))
      longId <- cellGeometry.longIdOption
      fid <- StoredFeatureId.fromLong(longId)
    } yield {
      if (!matches.has(fid)) {
        if (cellGeometry.full) {
          queryLogger.ifDebug("was full: %s", fid)
          matches.append(fid)
        } else {
          cellGeometry.geomOption match {
            case Some(geom) =>
              val intersects = queryLogger.logDuration("intersectionCheck", "intersectionCheck") {
                try {
                  geom.intersects(otherGeom)
                } catch {
                  case e: Throwable =>
                    Stats.addMetric("intersects_exception", 1)
                    logger.error("failed to calculate intersection: %s".format(otherGeom), e)
                    false
                }
              }
              if (intersects) {
                matches.append(fid)
              }
            case None => queryLogger.ifDebug("not full and no geometry for: %s", fid)
          }
        }
      }
    }

    matches.toSeq
  }

  def doBulkReverseGeocode(otherGeoms: Seq[Geometry]):
      (Seq[Seq[Int]], Seq[GeocodeInterpretation], Seq[GeocodeFeature]) = {
    // For each incoming geometry, get it's complete list of s2 cells
    val geomIndexToCellIdMap: Map[Int, Seq[Long]] = (for {
      (g, index) <- otherGeoms.zipWithIndex
    } yield { index -> s2CoverGeometry(g) }).toMap

    // Map of cellid -> Seq[CellGeometry]
    val cellGeometryMap: Map[Long, Seq[CellGeometryWrapper]] =
      (for {
        cellid: Long <- geomIndexToCellIdMap.values.flatten.toSet
      } yield {
        cellid -> store.getByS2CellId(cellid).map(cell => CellGeometryWrapper(cell, cellid))
      }).toMap

    // map from FeatureId -> Seq[candidate cells+CellGeometry]
    val featureIdToCellGeometryMap: Map[StoredFeatureId, Seq[CellGeometryWrapper]] = {
      cellGeometryMap.toList.flatMap({case (cellid, geometries) => geometries })
        .groupBy(cell => StoredFeatureId.fromLong(cell.longIdOption.getOrElse(0)).get)
    }

    val geomToMatches = (for {
      (otherGeom, index) <- otherGeoms.zipWithIndex
    } yield {
      val cellGeometries = geomIndexToCellIdMap(index).flatMap(cellid => cellGeometryMap(cellid))

      val featureIds = findMatches(otherGeom, cellGeometries)

      (otherGeom, featureIds)
    })

    val matchedIds = geomToMatches.flatMap(_._2).toSet.toList

    val polygonMap: Map[StoredFeatureId, Geometry] = (
      if (GeocodeRequestUtils.shouldFetchPolygon(req)) {
        store.getPolygonByFeatureIds(matchedIds)
      } else {
        Map.empty
      }
    )

    val servingFeaturesMap: Map[StoredFeatureId, GeocodeServingFeature] =
      store.getByFeatureIds(matchedIds)

    val parsesAndOtherGeomToFids: Seq[(SortedParseSeq, (Geometry, Seq[StoredFeatureId]))] = (for {
      ((otherGeom, featureIds), index) <- geomToMatches.zipWithIndex
    } yield {
      val servingFeaturesMap: Map[StoredFeatureId, GeocodeServingFeature] =
        store.getByFeatureIds(featureIds.toSet.toList)

      // for each, check if we're really in it
      val parses: SortedParseSeq = for {
	      fid <- featureIds
	      f <- servingFeaturesMap.get(fid)
      } yield {
        val parse = Parse[Sorted](List(FeatureMatch(0, 0, "", f)))
        if (responseIncludes(ResponseIncludes.REVGEO_COVERAGE)) {
          for {
            cellGeoms <- featureIdToCellGeometryMap.get(fid)
            totalArea <- f.scoringFeaturesOption.flatMap(_.areaInDegreesOption)
            // request overlap for now only makes sense for non-bulk lookups.
            // otherwise, overlap of which request is it describing?
            if otherGeoms.size == 1
            geom <- otherGeoms.headOption
          } {
            queryLogger.logDuration("intersectionCheck", "intersectionCheck") {
              val overlapArea = computeOverlapArea(cellGeoms, geom)
              val requestArea = geom.getArea()

              // coverage is undefined when the request is a point
              if (requestArea > 0) {
                parse.scoringFeatures.percentOfRequestCovered(math.min(1, overlapArea / requestArea))
                parse.scoringFeatures.percentOfFeatureCovered(math.min(1, overlapArea / totalArea))
              }
            }
          }
        }
        parse
      }

      val maxInterpretations = if (req.maxInterpretations <= 0) {
        parses.size
      } else {
        req.maxInterpretations
      }

      val sortedParses = parses.sorted(new ReverseGeocodeParseOrdering).take(maxInterpretations)

      (sortedParses, (otherGeom -> sortedParses.flatMap(p => StoredFeatureId.fromLong(p.fmatches(0).fmatch.longId))))
    })
    val sortedParses = parsesAndOtherGeomToFids.flatMap(_._1)
    val otherGeomToFids = parsesAndOtherGeomToFids.map(_._2).toMap

    val parseParams = ParseParams()

    val responseProcessor = new ResponseProcessor(req, store, queryLogger)
    val interpretations = responseProcessor.hydrateParses(sortedParses, parseParams, polygonMap,
      fixAmbiguousNames = false)

    makeBulkReply[Geometry](otherGeoms, otherGeomToFids, interpretations)
  }

  def getAllLevels(): Seq[Int] = {
    for {
      level <- store.getMinS2Level.to(store.getMaxS2Level)
      if ((level - store.getMinS2Level) % store.getLevelMod) == 0
    } yield { level }
  }
}

class ReverseGeocoderImpl(
  store: GeocodeStorageReadService,
  req: GeocodeRequest
) extends GeocoderImplTypes with TimeResponseHelper with Logging {
  val queryLogger = new MemoryLogger(req)
  val commonParams = GeocodeRequestUtils.geocodeRequestToCommonRequestParams(req)
  val reverseGeocoder =
    new ReverseGeocoderHelperImpl(store, commonParams, queryLogger)

  def doSingleReverseGeocode(geom: Geometry): GeocodeResponse = {
    val (interpIdxes, interpretations, _) = reverseGeocoder.doBulkReverseGeocode(List(geom))
    val response = ResponseProcessor.generateResponse(req.debug, queryLogger,
      interpIdxes(0).flatMap(interpIdx => interpretations.lift(interpIdx)),
      requestGeom = if (req.debug > 0) { Some(geom) } else { None })
    response
  }

  def reverseGeocodePoint(ll: GeocodePoint): GeocodeResponse = {
    val geomFactory = new GeometryFactory()
    val point = geomFactory.createPoint(new Coordinate(ll.lng, ll.lat))
    doSingleReverseGeocode(point)
  }


  def doGeometryReverseGeocode(geom: Geometry) = {
    doSingleReverseGeocode(geom)
  }

  def doCircleRevgeo(ll: GeocodePoint, radius: Int): GeocodeResponse = {
    if (req.radius > 50000) {
      //throw new Exception("radius too big (%d > %d)".format(req.radius, maxRadius))
      GeocodeResponse.newBuilder.interpretations(Nil).result
    } else {
      val sizeDegrees = req.radius / 111319.9
      val gsf = new GeometricShapeFactory()
      gsf.setSize(sizeDegrees)
      gsf.setNumPoints(100)
      gsf.setCentre(new Coordinate(ll.lng, ll.lat))
      val geom = gsf.createCircle()
      timeResponse("revgeo-geom") {
        doGeometryReverseGeocode(geom)
      }
    }
  }

  def doBoundsRevgeo(bounds: GeocodeBoundingBox): GeocodeResponse = {
    val s2rect = GeoTools.boundingBoxToS2Rect(bounds)
    val geomFactory = new GeometryFactory()
    val geom = geomFactory.createLinearRing(Array(
      new Coordinate(s2rect.lng.lo, s2rect.lat.lo),
      new Coordinate(s2rect.lng.hi, s2rect.lat.lo),
      new Coordinate(s2rect.lng.hi, s2rect.lat.hi),
      new Coordinate(s2rect.lng.hi, s2rect.lat.lo),
      new Coordinate(s2rect.lng.lo, s2rect.lat.lo)
    ))
    Stats.time("revgeo-geom") {
      doGeometryReverseGeocode(geom)
    }
  }

  def reverseGeocode(): GeocodeResponse = {
    Stats.incr("revgeo-requests", 1)

    val radius = req.radiusOption.getOrElse(0)
    (req.llOption, req.boundsOption) match {
      case (Some(ll), None) if (radius > 0) => doCircleRevgeo(ll, radius)
      case (Some(ll), None) => timeResponse("revgeo-point") { reverseGeocodePoint(ll) }
      case (None, Some(bounds)) => doBoundsRevgeo(bounds)
      case (None, None) => throw new Exception("no bounds or ll")
      case (Some(ll), Some(bounds)) => throw new Exception("both bounds and ll, can't pick")
    }
  }
}

class BulkReverseGeocoderImpl(
  store: GeocodeStorageReadService,
  req: BulkReverseGeocodeRequest
) extends GeocoderImplTypes with TimeResponseHelper {
  val params = req.paramsOption.getOrElse(CommonGeocodeRequestParams.newBuilder.result)

  val queryLogger = new MemoryLogger(params)
  val reverseGeocoder = new ReverseGeocoderHelperImpl(store, params, queryLogger)

  def reverseGeocode(): BulkReverseGeocodeResponse = {
    Stats.incr("bulk-revgeo-requests", 1)

    val geomFactory = new GeometryFactory()

    val points = req.latlngs.map(ll => geomFactory.createPoint(new Coordinate(ll.lng, ll.lat)))

    val (interpIdxs, interps, parents) = reverseGeocoder.doBulkReverseGeocode(points)

    val responseBuilder = BulkReverseGeocodeResponse.newBuilder
      .interpretationIndexes(interpIdxs)
      .interpretations(interps)
      .DEPRECATED_interpretationMap(Map.empty)
      .parentFeatures(parents)

    if (params.debug > 0) {
      responseBuilder.debugLines(queryLogger.getLines)
    }
    responseBuilder.result
  }
}

