name := "scala-workshop"

version := "0.1.0-SNAPSHOT"

organization := "com.yanns"

scalaVersion := "2.11.1"

scalacOptions ++= Seq("-Xmax-classfile-name", "128", "-deprecation","-unchecked")

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.3",
  "org.joda" % "joda-convert" % "1.6",
  "org.scala-lang.modules" %% "scala-async" % "0.9.1",
  "com.typesafe.akka" %% "akka-actor" % "2.3.4"
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.0",
  "org.mockito" % "mockito-core" % "1.9.5",
  "org.specs2" %% "specs2" % "2.3.12",
  "org.scalacheck" %% "scalacheck" % "1.11.4"
).map(_ % "test")

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")

lazy val macros = project
  .settings(scalaVersion := "2.11.1")
  .settings(libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value)

lazy val root =
  project.in( file(".") )
    .dependsOn(macros)

