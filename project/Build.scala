import sbt.Keys._
import sbt.{Resolver, _}

object Common {

  def projectSettings = Seq(
    scalaVersion := "2.12.8",
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    scalacOptions ++= Seq(
      "-encoding", "UTF-8", // yes, this is 2 args
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Xlint",
      "-Yno-adapted-args",
      "-Ywarn-numeric-widen"
    ),
    resolvers ++= Seq(
      "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
       Resolver.sonatypeRepo("releases"),
       Resolver.sonatypeRepo("snapshots")),
    libraryDependencies ++= Seq(
      "javax.inject" % "javax.inject" % "1",
      "joda-time" % "joda-time" % "2.10.1",
      "org.joda" % "joda-convert" % "2.1.2",
      "com.google.inject" % "guice" % "4.2.2"
    ),
    scalacOptions in Test ++= Seq("-Yrangepos")
  )
}
