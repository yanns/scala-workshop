// Comment to get more information during initialization
logLevel := Level.Warn

// intellj integration
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.2")

// eclipse integration
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.4.0")

// dependency graph
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.4")

// dependency updates
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.1.4")
