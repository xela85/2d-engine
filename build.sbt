import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

// This is an application with a main method
scalaJSUseMainModuleInitializer := true
lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "JSGame",
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.1.0",
    libraryDependencies += "org.typelevel"  %%% "squants"  % "1.6.0",
    libraryDependencies += "org.typelevel" %%% "cats-core" % "2.3.1",
    libraryDependencies += scalaTest % Test
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
