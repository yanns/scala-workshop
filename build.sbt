name := "scala-workshop"

version := "0.1.0-SNAPSHOT"

organization := "com.yanns"

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-Xmax-classfile-name", "128", "-deprecation","-unchecked")

libraryDependencies += "joda-time" % "joda-time" % "2.3"

libraryDependencies += "org.joda" % "joda-convert" % "1.5"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.0" % "test"

libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"

libraryDependencies += "org.specs2" %% "specs2" % "2.1.1" % "test"
 
scalacOptions in Test ++= Seq("-Yrangepos")

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")

