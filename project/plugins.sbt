addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.8.6")

addSbtPlugin("com.foursquare" % "spindle-codegen-plugin" % "1.5.0")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.4")

resolvers += Resolver.url("sbt-plugin-releases",
  new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)
