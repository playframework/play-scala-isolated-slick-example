name := """play-slick-3.0"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "javax.inject" % "javax.inject" % "1",
  "net.codingwell" %% "scala-guice" % "4.0.0",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "com.zaxxer" % "HikariCP" % "2.3.8",
  "com.typesafe.slick" %% "slick" % "3.0.0"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers += Resolver.sonatypeRepo("releases")

resolvers += Resolver.sonatypeRepo("snapshots")
