name := "scala-workshop"

version := "0.1.0-SNAPSHOT"

organization := "com.yanns"

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-Xmax-classfile-name", "128", "-deprecation","-unchecked")

libraryDependencies += "joda-time" % "joda-time" % "2.3"

libraryDependencies += "org.joda" % "joda-convert" % "1.5"

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.0-M4"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.2.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.0.1-SNAP6" % "test"

libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"

libraryDependencies += "org.specs2" %% "specs2" % "2.3.7" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.10.1" % "test"
 
resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")

lazy val macros = project
  .settings(
    libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.10.2"
  )

lazy val root =
  project.in( file(".") )
    .dependsOn(macros)

