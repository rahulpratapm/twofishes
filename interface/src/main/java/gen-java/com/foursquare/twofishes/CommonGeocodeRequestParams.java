/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.foursquare.twofishes;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.thrift.*;
import org.apache.thrift.async.*;
import org.apache.thrift.meta_data.*;
import org.apache.thrift.transport.*;
import org.apache.thrift.protocol.*;

// No additional import required for struct/union.

public class CommonGeocodeRequestParams implements TBase<CommonGeocodeRequestParams, CommonGeocodeRequestParams._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("CommonGeocodeRequestParams");

  private static final TField DEBUG_FIELD_DESC = new TField("debug", TType.I32, (short)1);
  private static final TField WOE_HINT_FIELD_DESC = new TField("woeHint", TType.LIST, (short)2);
  private static final TField WOE_RESTRICT_FIELD_DESC = new TField("woeRestrict", TType.LIST, (short)3);
  private static final TField CC_FIELD_DESC = new TField("cc", TType.STRING, (short)4);
  private static final TField LANG_FIELD_DESC = new TField("lang", TType.STRING, (short)5);
  private static final TField RESPONSE_INCLUDES_FIELD_DESC = new TField("responseIncludes", TType.LIST, (short)6);
  private static final TField ALLOWED_SOURCES_FIELD_DESC = new TField("allowedSources", TType.LIST, (short)7);
  private static final TField LL_HINT_FIELD_DESC = new TField("llHint", TType.STRUCT, (short)8);
  private static final TField BOUNDS_FIELD_DESC = new TField("bounds", TType.STRUCT, (short)9);
  private static final TField MAX_INTERPRETATIONS_FIELD_DESC = new TField("maxInterpretations", TType.I32, (short)10);

  public int debug;
  public List<YahooWoeType> woeHint;
  public List<YahooWoeType> woeRestrict;
  public String cc;
  public String lang;
  public List<ResponseIncludes> responseIncludes;
  public List<String> allowedSources;
  public GeocodePoint llHint;
  public GeocodeBoundingBox bounds;
  public int maxInterpretations;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    DEBUG((short)1, "debug"),
    WOE_HINT((short)2, "woeHint"),
    WOE_RESTRICT((short)3, "woeRestrict"),
    CC((short)4, "cc"),
    LANG((short)5, "lang"),
    RESPONSE_INCLUDES((short)6, "responseIncludes"),
    ALLOWED_SOURCES((short)7, "allowedSources"),
    LL_HINT((short)8, "llHint"),
    BOUNDS((short)9, "bounds"),
    MAX_INTERPRETATIONS((short)10, "maxInterpretations");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // DEBUG
          return DEBUG;
        case 2: // WOE_HINT
          return WOE_HINT;
        case 3: // WOE_RESTRICT
          return WOE_RESTRICT;
        case 4: // CC
          return CC;
        case 5: // LANG
          return LANG;
        case 6: // RESPONSE_INCLUDES
          return RESPONSE_INCLUDES;
        case 7: // ALLOWED_SOURCES
          return ALLOWED_SOURCES;
        case 8: // LL_HINT
          return LL_HINT;
        case 9: // BOUNDS
          return BOUNDS;
        case 10: // MAX_INTERPRETATIONS
          return MAX_INTERPRETATIONS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __DEBUG_ISSET_ID = 0;
  private static final int __MAXINTERPRETATIONS_ISSET_ID = 1;
  private BitSet __isset_bit_vector = new BitSet(2);

  public static final Map<_Fields, FieldMetaData> metaDataMap;
  static {
    Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DEBUG, new FieldMetaData("debug", TFieldRequirementType.OPTIONAL, 
        new FieldValueMetaData(TType.I32)));
    tmpMap.put(_Fields.WOE_HINT, new FieldMetaData("woeHint", TFieldRequirementType.OPTIONAL, 
        new ListMetaData(TType.LIST, 
            new EnumMetaData(TType.ENUM, YahooWoeType.class))));
    tmpMap.put(_Fields.WOE_RESTRICT, new FieldMetaData("woeRestrict", TFieldRequirementType.OPTIONAL, 
        new ListMetaData(TType.LIST, 
            new EnumMetaData(TType.ENUM, YahooWoeType.class))));
    tmpMap.put(_Fields.CC, new FieldMetaData("cc", TFieldRequirementType.OPTIONAL, 
        new FieldValueMetaData(TType.STRING)));
    tmpMap.put(_Fields.LANG, new FieldMetaData("lang", TFieldRequirementType.OPTIONAL, 
        new FieldValueMetaData(TType.STRING)));
    tmpMap.put(_Fields.RESPONSE_INCLUDES, new FieldMetaData("responseIncludes", TFieldRequirementType.OPTIONAL, 
        new ListMetaData(TType.LIST, 
            new EnumMetaData(TType.ENUM, ResponseIncludes.class))));
    tmpMap.put(_Fields.ALLOWED_SOURCES, new FieldMetaData("allowedSources", TFieldRequirementType.OPTIONAL, 
        new ListMetaData(TType.LIST, 
            new FieldValueMetaData(TType.STRING))));
    tmpMap.put(_Fields.LL_HINT, new FieldMetaData("llHint", TFieldRequirementType.OPTIONAL, 
        new StructMetaData(TType.STRUCT, GeocodePoint.class)));
    tmpMap.put(_Fields.BOUNDS, new FieldMetaData("bounds", TFieldRequirementType.OPTIONAL, 
        new StructMetaData(TType.STRUCT, GeocodeBoundingBox.class)));
    tmpMap.put(_Fields.MAX_INTERPRETATIONS, new FieldMetaData("maxInterpretations", TFieldRequirementType.OPTIONAL, 
        new FieldValueMetaData(TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(CommonGeocodeRequestParams.class, metaDataMap);
  }

  public CommonGeocodeRequestParams() {
    this.debug = 0;

    this.woeHint = new ArrayList<YahooWoeType>();

    this.woeRestrict = new ArrayList<YahooWoeType>();

    this.lang = "en";

    this.responseIncludes = new ArrayList<ResponseIncludes>();

    this.maxInterpretations = 0;

  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CommonGeocodeRequestParams(CommonGeocodeRequestParams other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.debug = other.debug;
    if (other.isSetWoeHint()) {
      List<YahooWoeType> __this__woeHint = new ArrayList<YahooWoeType>();
      for (YahooWoeType other_element : other.woeHint) {
        __this__woeHint.add(other_element);
      }
      this.woeHint = __this__woeHint;
    }
    if (other.isSetWoeRestrict()) {
      List<YahooWoeType> __this__woeRestrict = new ArrayList<YahooWoeType>();
      for (YahooWoeType other_element : other.woeRestrict) {
        __this__woeRestrict.add(other_element);
      }
      this.woeRestrict = __this__woeRestrict;
    }
    if (other.isSetCc()) {
      this.cc = other.cc;
    }
    if (other.isSetLang()) {
      this.lang = other.lang;
    }
    if (other.isSetResponseIncludes()) {
      List<ResponseIncludes> __this__responseIncludes = new ArrayList<ResponseIncludes>();
      for (ResponseIncludes other_element : other.responseIncludes) {
        __this__responseIncludes.add(other_element);
      }
      this.responseIncludes = __this__responseIncludes;
    }
    if (other.isSetAllowedSources()) {
      List<String> __this__allowedSources = new ArrayList<String>();
      for (String other_element : other.allowedSources) {
        __this__allowedSources.add(other_element);
      }
      this.allowedSources = __this__allowedSources;
    }
    if (other.isSetLlHint()) {
      this.llHint = new GeocodePoint(other.llHint);
    }
    if (other.isSetBounds()) {
      this.bounds = new GeocodeBoundingBox(other.bounds);
    }
    this.maxInterpretations = other.maxInterpretations;
  }

  public CommonGeocodeRequestParams deepCopy() {
    return new CommonGeocodeRequestParams(this);
  }

  @Override
  public void clear() {
    this.debug = 0;

    this.woeHint = new ArrayList<YahooWoeType>();

    this.woeRestrict = new ArrayList<YahooWoeType>();

    this.cc = null;
    this.lang = "en";

    this.responseIncludes = new ArrayList<ResponseIncludes>();

    this.allowedSources = null;
    this.llHint = null;
    this.bounds = null;
    this.maxInterpretations = 0;

  }

  public int getDebug() {
    return this.debug;
  }

  public CommonGeocodeRequestParams setDebug(int debug) {
    this.debug = debug;
    setDebugIsSet(true);
    return this;
  }

  public void unsetDebug() {
    __isset_bit_vector.clear(__DEBUG_ISSET_ID);
  }

  /** Returns true if field debug is set (has been asigned a value) and false otherwise */
  public boolean isSetDebug() {
    return __isset_bit_vector.get(__DEBUG_ISSET_ID);
  }

  public void setDebugIsSet(boolean value) {
    __isset_bit_vector.set(__DEBUG_ISSET_ID, value);
  }

  public int getWoeHintSize() {
    return (this.woeHint == null) ? 0 : this.woeHint.size();
  }

  public java.util.Iterator<YahooWoeType> getWoeHintIterator() {
    return (this.woeHint == null) ? null : this.woeHint.iterator();
  }

  public void addToWoeHint(YahooWoeType elem) {
    if (this.woeHint == null) {
      this.woeHint = new ArrayList<YahooWoeType>();
    }
    this.woeHint.add(elem);
  }

  public List<YahooWoeType> getWoeHint() {
    return this.woeHint;
  }

  public CommonGeocodeRequestParams setWoeHint(List<YahooWoeType> woeHint) {
    this.woeHint = woeHint;
    return this;
  }

  public void unsetWoeHint() {
    this.woeHint = null;
  }

  /** Returns true if field woeHint is set (has been asigned a value) and false otherwise */
  public boolean isSetWoeHint() {
    return this.woeHint != null;
  }

  public void setWoeHintIsSet(boolean value) {
    if (!value) {
      this.woeHint = null;
    }
  }

  public int getWoeRestrictSize() {
    return (this.woeRestrict == null) ? 0 : this.woeRestrict.size();
  }

  public java.util.Iterator<YahooWoeType> getWoeRestrictIterator() {
    return (this.woeRestrict == null) ? null : this.woeRestrict.iterator();
  }

  public void addToWoeRestrict(YahooWoeType elem) {
    if (this.woeRestrict == null) {
      this.woeRestrict = new ArrayList<YahooWoeType>();
    }
    this.woeRestrict.add(elem);
  }

  public List<YahooWoeType> getWoeRestrict() {
    return this.woeRestrict;
  }

  public CommonGeocodeRequestParams setWoeRestrict(List<YahooWoeType> woeRestrict) {
    this.woeRestrict = woeRestrict;
    return this;
  }

  public void unsetWoeRestrict() {
    this.woeRestrict = null;
  }

  /** Returns true if field woeRestrict is set (has been asigned a value) and false otherwise */
  public boolean isSetWoeRestrict() {
    return this.woeRestrict != null;
  }

  public void setWoeRestrictIsSet(boolean value) {
    if (!value) {
      this.woeRestrict = null;
    }
  }

  public String getCc() {
    return this.cc;
  }

  public CommonGeocodeRequestParams setCc(String cc) {
    this.cc = cc;
    return this;
  }

  public void unsetCc() {
    this.cc = null;
  }

  /** Returns true if field cc is set (has been asigned a value) and false otherwise */
  public boolean isSetCc() {
    return this.cc != null;
  }

  public void setCcIsSet(boolean value) {
    if (!value) {
      this.cc = null;
    }
  }

  public String getLang() {
    return this.lang;
  }

  public CommonGeocodeRequestParams setLang(String lang) {
    this.lang = lang;
    return this;
  }

  public void unsetLang() {
    this.lang = null;
  }

  /** Returns true if field lang is set (has been asigned a value) and false otherwise */
  public boolean isSetLang() {
    return this.lang != null;
  }

  public void setLangIsSet(boolean value) {
    if (!value) {
      this.lang = null;
    }
  }

  public int getResponseIncludesSize() {
    return (this.responseIncludes == null) ? 0 : this.responseIncludes.size();
  }

  public java.util.Iterator<ResponseIncludes> getResponseIncludesIterator() {
    return (this.responseIncludes == null) ? null : this.responseIncludes.iterator();
  }

  public void addToResponseIncludes(ResponseIncludes elem) {
    if (this.responseIncludes == null) {
      this.responseIncludes = new ArrayList<ResponseIncludes>();
    }
    this.responseIncludes.add(elem);
  }

  public List<ResponseIncludes> getResponseIncludes() {
    return this.responseIncludes;
  }

  public CommonGeocodeRequestParams setResponseIncludes(List<ResponseIncludes> responseIncludes) {
    this.responseIncludes = responseIncludes;
    return this;
  }

  public void unsetResponseIncludes() {
    this.responseIncludes = null;
  }

  /** Returns true if field responseIncludes is set (has been asigned a value) and false otherwise */
  public boolean isSetResponseIncludes() {
    return this.responseIncludes != null;
  }

  public void setResponseIncludesIsSet(boolean value) {
    if (!value) {
      this.responseIncludes = null;
    }
  }

  public int getAllowedSourcesSize() {
    return (this.allowedSources == null) ? 0 : this.allowedSources.size();
  }

  public java.util.Iterator<String> getAllowedSourcesIterator() {
    return (this.allowedSources == null) ? null : this.allowedSources.iterator();
  }

  public void addToAllowedSources(String elem) {
    if (this.allowedSources == null) {
      this.allowedSources = new ArrayList<String>();
    }
    this.allowedSources.add(elem);
  }

  public List<String> getAllowedSources() {
    return this.allowedSources;
  }

  public CommonGeocodeRequestParams setAllowedSources(List<String> allowedSources) {
    this.allowedSources = allowedSources;
    return this;
  }

  public void unsetAllowedSources() {
    this.allowedSources = null;
  }

  /** Returns true if field allowedSources is set (has been asigned a value) and false otherwise */
  public boolean isSetAllowedSources() {
    return this.allowedSources != null;
  }

  public void setAllowedSourcesIsSet(boolean value) {
    if (!value) {
      this.allowedSources = null;
    }
  }

  public GeocodePoint getLlHint() {
    return this.llHint;
  }

  public CommonGeocodeRequestParams setLlHint(GeocodePoint llHint) {
    this.llHint = llHint;
    return this;
  }

  public void unsetLlHint() {
    this.llHint = null;
  }

  /** Returns true if field llHint is set (has been asigned a value) and false otherwise */
  public boolean isSetLlHint() {
    return this.llHint != null;
  }

  public void setLlHintIsSet(boolean value) {
    if (!value) {
      this.llHint = null;
    }
  }

  public GeocodeBoundingBox getBounds() {
    return this.bounds;
  }

  public CommonGeocodeRequestParams setBounds(GeocodeBoundingBox bounds) {
    this.bounds = bounds;
    return this;
  }

  public void unsetBounds() {
    this.bounds = null;
  }

  /** Returns true if field bounds is set (has been asigned a value) and false otherwise */
  public boolean isSetBounds() {
    return this.bounds != null;
  }

  public void setBoundsIsSet(boolean value) {
    if (!value) {
      this.bounds = null;
    }
  }

  public int getMaxInterpretations() {
    return this.maxInterpretations;
  }

  public CommonGeocodeRequestParams setMaxInterpretations(int maxInterpretations) {
    this.maxInterpretations = maxInterpretations;
    setMaxInterpretationsIsSet(true);
    return this;
  }

  public void unsetMaxInterpretations() {
    __isset_bit_vector.clear(__MAXINTERPRETATIONS_ISSET_ID);
  }

  /** Returns true if field maxInterpretations is set (has been asigned a value) and false otherwise */
  public boolean isSetMaxInterpretations() {
    return __isset_bit_vector.get(__MAXINTERPRETATIONS_ISSET_ID);
  }

  public void setMaxInterpretationsIsSet(boolean value) {
    __isset_bit_vector.set(__MAXINTERPRETATIONS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case DEBUG:
      if (value == null) {
        unsetDebug();
      } else {
        setDebug((Integer)value);
      }
      break;

    case WOE_HINT:
      if (value == null) {
        unsetWoeHint();
      } else {
        setWoeHint((List<YahooWoeType>)value);
      }
      break;

    case WOE_RESTRICT:
      if (value == null) {
        unsetWoeRestrict();
      } else {
        setWoeRestrict((List<YahooWoeType>)value);
      }
      break;

    case CC:
      if (value == null) {
        unsetCc();
      } else {
        setCc((String)value);
      }
      break;

    case LANG:
      if (value == null) {
        unsetLang();
      } else {
        setLang((String)value);
      }
      break;

    case RESPONSE_INCLUDES:
      if (value == null) {
        unsetResponseIncludes();
      } else {
        setResponseIncludes((List<ResponseIncludes>)value);
      }
      break;

    case ALLOWED_SOURCES:
      if (value == null) {
        unsetAllowedSources();
      } else {
        setAllowedSources((List<String>)value);
      }
      break;

    case LL_HINT:
      if (value == null) {
        unsetLlHint();
      } else {
        setLlHint((GeocodePoint)value);
      }
      break;

    case BOUNDS:
      if (value == null) {
        unsetBounds();
      } else {
        setBounds((GeocodeBoundingBox)value);
      }
      break;

    case MAX_INTERPRETATIONS:
      if (value == null) {
        unsetMaxInterpretations();
      } else {
        setMaxInterpretations((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case DEBUG:
      return new Integer(getDebug());

    case WOE_HINT:
      return getWoeHint();

    case WOE_RESTRICT:
      return getWoeRestrict();

    case CC:
      return getCc();

    case LANG:
      return getLang();

    case RESPONSE_INCLUDES:
      return getResponseIncludes();

    case ALLOWED_SOURCES:
      return getAllowedSources();

    case LL_HINT:
      return getLlHint();

    case BOUNDS:
      return getBounds();

    case MAX_INTERPRETATIONS:
      return new Integer(getMaxInterpretations());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case DEBUG:
      return isSetDebug();
    case WOE_HINT:
      return isSetWoeHint();
    case WOE_RESTRICT:
      return isSetWoeRestrict();
    case CC:
      return isSetCc();
    case LANG:
      return isSetLang();
    case RESPONSE_INCLUDES:
      return isSetResponseIncludes();
    case ALLOWED_SOURCES:
      return isSetAllowedSources();
    case LL_HINT:
      return isSetLlHint();
    case BOUNDS:
      return isSetBounds();
    case MAX_INTERPRETATIONS:
      return isSetMaxInterpretations();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CommonGeocodeRequestParams)
      return this.equals((CommonGeocodeRequestParams)that);
    return false;
  }

  public boolean equals(CommonGeocodeRequestParams that) {
    if (that == null)
      return false;

    boolean this_present_debug = true && this.isSetDebug();
    boolean that_present_debug = true && that.isSetDebug();
    if (this_present_debug || that_present_debug) {
      if (!(this_present_debug && that_present_debug))
        return false;
      if (this.debug != that.debug)
        return false;
    }

    boolean this_present_woeHint = true && this.isSetWoeHint();
    boolean that_present_woeHint = true && that.isSetWoeHint();
    if (this_present_woeHint || that_present_woeHint) {
      if (!(this_present_woeHint && that_present_woeHint))
        return false;
      if (!this.woeHint.equals(that.woeHint))
        return false;
    }

    boolean this_present_woeRestrict = true && this.isSetWoeRestrict();
    boolean that_present_woeRestrict = true && that.isSetWoeRestrict();
    if (this_present_woeRestrict || that_present_woeRestrict) {
      if (!(this_present_woeRestrict && that_present_woeRestrict))
        return false;
      if (!this.woeRestrict.equals(that.woeRestrict))
        return false;
    }

    boolean this_present_cc = true && this.isSetCc();
    boolean that_present_cc = true && that.isSetCc();
    if (this_present_cc || that_present_cc) {
      if (!(this_present_cc && that_present_cc))
        return false;
      if (!this.cc.equals(that.cc))
        return false;
    }

    boolean this_present_lang = true && this.isSetLang();
    boolean that_present_lang = true && that.isSetLang();
    if (this_present_lang || that_present_lang) {
      if (!(this_present_lang && that_present_lang))
        return false;
      if (!this.lang.equals(that.lang))
        return false;
    }

    boolean this_present_responseIncludes = true && this.isSetResponseIncludes();
    boolean that_present_responseIncludes = true && that.isSetResponseIncludes();
    if (this_present_responseIncludes || that_present_responseIncludes) {
      if (!(this_present_responseIncludes && that_present_responseIncludes))
        return false;
      if (!this.responseIncludes.equals(that.responseIncludes))
        return false;
    }

    boolean this_present_allowedSources = true && this.isSetAllowedSources();
    boolean that_present_allowedSources = true && that.isSetAllowedSources();
    if (this_present_allowedSources || that_present_allowedSources) {
      if (!(this_present_allowedSources && that_present_allowedSources))
        return false;
      if (!this.allowedSources.equals(that.allowedSources))
        return false;
    }

    boolean this_present_llHint = true && this.isSetLlHint();
    boolean that_present_llHint = true && that.isSetLlHint();
    if (this_present_llHint || that_present_llHint) {
      if (!(this_present_llHint && that_present_llHint))
        return false;
      if (!this.llHint.equals(that.llHint))
        return false;
    }

    boolean this_present_bounds = true && this.isSetBounds();
    boolean that_present_bounds = true && that.isSetBounds();
    if (this_present_bounds || that_present_bounds) {
      if (!(this_present_bounds && that_present_bounds))
        return false;
      if (!this.bounds.equals(that.bounds))
        return false;
    }

    boolean this_present_maxInterpretations = true && this.isSetMaxInterpretations();
    boolean that_present_maxInterpretations = true && that.isSetMaxInterpretations();
    if (this_present_maxInterpretations || that_present_maxInterpretations) {
      if (!(this_present_maxInterpretations && that_present_maxInterpretations))
        return false;
      if (this.maxInterpretations != that.maxInterpretations)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(CommonGeocodeRequestParams other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    CommonGeocodeRequestParams typedOther = (CommonGeocodeRequestParams)other;

    lastComparison = Boolean.valueOf(isSetDebug()).compareTo(typedOther.isSetDebug());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDebug()) {
      lastComparison = TBaseHelper.compareTo(this.debug, typedOther.debug);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWoeHint()).compareTo(typedOther.isSetWoeHint());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWoeHint()) {
      lastComparison = TBaseHelper.compareTo(this.woeHint, typedOther.woeHint);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWoeRestrict()).compareTo(typedOther.isSetWoeRestrict());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWoeRestrict()) {
      lastComparison = TBaseHelper.compareTo(this.woeRestrict, typedOther.woeRestrict);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCc()).compareTo(typedOther.isSetCc());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCc()) {
      lastComparison = TBaseHelper.compareTo(this.cc, typedOther.cc);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLang()).compareTo(typedOther.isSetLang());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLang()) {
      lastComparison = TBaseHelper.compareTo(this.lang, typedOther.lang);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetResponseIncludes()).compareTo(typedOther.isSetResponseIncludes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResponseIncludes()) {
      lastComparison = TBaseHelper.compareTo(this.responseIncludes, typedOther.responseIncludes);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAllowedSources()).compareTo(typedOther.isSetAllowedSources());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAllowedSources()) {
      lastComparison = TBaseHelper.compareTo(this.allowedSources, typedOther.allowedSources);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLlHint()).compareTo(typedOther.isSetLlHint());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLlHint()) {
      lastComparison = TBaseHelper.compareTo(this.llHint, typedOther.llHint);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBounds()).compareTo(typedOther.isSetBounds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBounds()) {
      lastComparison = TBaseHelper.compareTo(this.bounds, typedOther.bounds);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMaxInterpretations()).compareTo(typedOther.isSetMaxInterpretations());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMaxInterpretations()) {
      lastComparison = TBaseHelper.compareTo(this.maxInterpretations, typedOther.maxInterpretations);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) { 
        break;
      }
      switch (field.id) {
        case 1: // DEBUG
          if (field.type == TType.I32) {
            this.debug = iprot.readI32();
            setDebugIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // WOE_HINT
          if (field.type == TType.LIST) {
            {
              TList _list64 = iprot.readListBegin();
              this.woeHint = new ArrayList<YahooWoeType>(_list64.size);
              for (int _i65 = 0; _i65 < _list64.size; ++_i65)
              {
                YahooWoeType _elem66;
                _elem66 = YahooWoeType.findByValue(iprot.readI32());
                this.woeHint.add(_elem66);
              }
              iprot.readListEnd();
            }
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // WOE_RESTRICT
          if (field.type == TType.LIST) {
            {
              TList _list67 = iprot.readListBegin();
              this.woeRestrict = new ArrayList<YahooWoeType>(_list67.size);
              for (int _i68 = 0; _i68 < _list67.size; ++_i68)
              {
                YahooWoeType _elem69;
                _elem69 = YahooWoeType.findByValue(iprot.readI32());
                this.woeRestrict.add(_elem69);
              }
              iprot.readListEnd();
            }
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 4: // CC
          if (field.type == TType.STRING) {
            this.cc = iprot.readString();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 5: // LANG
          if (field.type == TType.STRING) {
            this.lang = iprot.readString();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 6: // RESPONSE_INCLUDES
          if (field.type == TType.LIST) {
            {
              TList _list70 = iprot.readListBegin();
              this.responseIncludes = new ArrayList<ResponseIncludes>(_list70.size);
              for (int _i71 = 0; _i71 < _list70.size; ++_i71)
              {
                ResponseIncludes _elem72;
                _elem72 = ResponseIncludes.findByValue(iprot.readI32());
                this.responseIncludes.add(_elem72);
              }
              iprot.readListEnd();
            }
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 7: // ALLOWED_SOURCES
          if (field.type == TType.LIST) {
            {
              TList _list73 = iprot.readListBegin();
              this.allowedSources = new ArrayList<String>(_list73.size);
              for (int _i74 = 0; _i74 < _list73.size; ++_i74)
              {
                String _elem75;
                _elem75 = iprot.readString();
                this.allowedSources.add(_elem75);
              }
              iprot.readListEnd();
            }
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 8: // LL_HINT
          if (field.type == TType.STRUCT) {
            this.llHint = new GeocodePoint();
            this.llHint.read(iprot);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 9: // BOUNDS
          if (field.type == TType.STRUCT) {
            this.bounds = new GeocodeBoundingBox();
            this.bounds.read(iprot);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 10: // MAX_INTERPRETATIONS
          if (field.type == TType.I32) {
            this.maxInterpretations = iprot.readI32();
            setMaxInterpretationsIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    if (isSetDebug()) {
      oprot.writeFieldBegin(DEBUG_FIELD_DESC);
      oprot.writeI32(this.debug);
      oprot.writeFieldEnd();
    }
    if (this.woeHint != null) {
      if (isSetWoeHint()) {
        oprot.writeFieldBegin(WOE_HINT_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.I32, this.woeHint.size()));
          for (YahooWoeType _iter76 : this.woeHint)
          {
            oprot.writeI32(_iter76.getValue());
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
    }
    if (this.woeRestrict != null) {
      if (isSetWoeRestrict()) {
        oprot.writeFieldBegin(WOE_RESTRICT_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.I32, this.woeRestrict.size()));
          for (YahooWoeType _iter77 : this.woeRestrict)
          {
            oprot.writeI32(_iter77.getValue());
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
    }
    if (this.cc != null) {
      if (isSetCc()) {
        oprot.writeFieldBegin(CC_FIELD_DESC);
        oprot.writeString(this.cc);
        oprot.writeFieldEnd();
      }
    }
    if (this.lang != null) {
      if (isSetLang()) {
        oprot.writeFieldBegin(LANG_FIELD_DESC);
        oprot.writeString(this.lang);
        oprot.writeFieldEnd();
      }
    }
    if (this.responseIncludes != null) {
      if (isSetResponseIncludes()) {
        oprot.writeFieldBegin(RESPONSE_INCLUDES_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.I32, this.responseIncludes.size()));
          for (ResponseIncludes _iter78 : this.responseIncludes)
          {
            oprot.writeI32(_iter78.getValue());
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
    }
    if (this.allowedSources != null) {
      if (isSetAllowedSources()) {
        oprot.writeFieldBegin(ALLOWED_SOURCES_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRING, this.allowedSources.size()));
          for (String _iter79 : this.allowedSources)
          {
            oprot.writeString(_iter79);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
    }
    if (this.llHint != null) {
      if (isSetLlHint()) {
        oprot.writeFieldBegin(LL_HINT_FIELD_DESC);
        this.llHint.write(oprot);
        oprot.writeFieldEnd();
      }
    }
    if (this.bounds != null) {
      if (isSetBounds()) {
        oprot.writeFieldBegin(BOUNDS_FIELD_DESC);
        this.bounds.write(oprot);
        oprot.writeFieldEnd();
      }
    }
    if (isSetMaxInterpretations()) {
      oprot.writeFieldBegin(MAX_INTERPRETATIONS_FIELD_DESC);
      oprot.writeI32(this.maxInterpretations);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("CommonGeocodeRequestParams(");
    boolean first = true;

    if (isSetDebug()) {
      sb.append("debug:");
      sb.append(this.debug);
      first = false;
    }
    if (isSetWoeHint()) {
      if (!first) sb.append(", ");
      sb.append("woeHint:");
      if (this.woeHint == null) {
        sb.append("null");
      } else {
        sb.append(this.woeHint);
      }
      first = false;
    }
    if (isSetWoeRestrict()) {
      if (!first) sb.append(", ");
      sb.append("woeRestrict:");
      if (this.woeRestrict == null) {
        sb.append("null");
      } else {
        sb.append(this.woeRestrict);
      }
      first = false;
    }
    if (isSetCc()) {
      if (!first) sb.append(", ");
      sb.append("cc:");
      if (this.cc == null) {
        sb.append("null");
      } else {
        sb.append(this.cc);
      }
      first = false;
    }
    if (isSetLang()) {
      if (!first) sb.append(", ");
      sb.append("lang:");
      if (this.lang == null) {
        sb.append("null");
      } else {
        sb.append(this.lang);
      }
      first = false;
    }
    if (isSetResponseIncludes()) {
      if (!first) sb.append(", ");
      sb.append("responseIncludes:");
      if (this.responseIncludes == null) {
        sb.append("null");
      } else {
        sb.append(this.responseIncludes);
      }
      first = false;
    }
    if (isSetAllowedSources()) {
      if (!first) sb.append(", ");
      sb.append("allowedSources:");
      if (this.allowedSources == null) {
        sb.append("null");
      } else {
        sb.append(this.allowedSources);
      }
      first = false;
    }
    if (isSetLlHint()) {
      if (!first) sb.append(", ");
      sb.append("llHint:");
      if (this.llHint == null) {
        sb.append("null");
      } else {
        sb.append(this.llHint);
      }
      first = false;
    }
    if (isSetBounds()) {
      if (!first) sb.append(", ");
      sb.append("bounds:");
      if (this.bounds == null) {
        sb.append("null");
      } else {
        sb.append(this.bounds);
      }
      first = false;
    }
    if (isSetMaxInterpretations()) {
      if (!first) sb.append(", ");
      sb.append("maxInterpretations:");
      sb.append(this.maxInterpretations);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }

}

