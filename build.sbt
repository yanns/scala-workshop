name := "scala-workshop"

version := "0.1.0-SNAPSHOT"

organization := "com.yanns"

scalaVersion := "2.11.7"

scalacOptions ++= Seq(
  "-Xmax-classfile-name", "128",
  "-deprecation",
  "-unchecked",
  "-feature")

javacOptions ++= Seq(
  "-deprecation",
  "-Xlint:unchecked")

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.8.1",
  "org.joda" % "joda-convert" % "1.7",
  "org.scala-lang.modules" %% "scala-async" % "0.9.4",
  "com.typesafe.akka" %% "akka-actor" % "2.3.12")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.5",
  "org.mockito" % "mockito-core" % "1.10.19",
  "org.scalacheck" %% "scalacheck" % "1.12.4"
).map(_ % "test")

lazy val macros = project

lazy val root =
  project.in( file(".") )
    .dependsOn(macros)

