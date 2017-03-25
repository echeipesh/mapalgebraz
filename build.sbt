scalaVersion := Version.scala

lazy val commonSettings = Seq(
  scalaVersion := Version.scala,
  description := "Functional map algebra model",
  organization := "map",
  licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html")),
  scalacOptions ++= Seq(
    "-deprecation",
    "-unchecked",
    "-Yinline-warnings",
    "-language:implicitConversions",
    "-language:reflectiveCalls",
    "-language:higherKinds",
    "-language:postfixOps",
    "-language:existentials",
    "-feature"),

  libraryDependencies ++= Seq(
    "org.scalatest"               %% "scalatest"         % Version.scalaTest % "test",
    "io.circe"                    %% "circe-core"        % "0.7.0",
    "io.circe"                    %% "circe-generic"     % "0.7.0",
    "io.circe"                    %% "circe-parser"      % "0.7.0",
    "io.circe"                    %% "circe-optics"      % "0.7.0",
    "com.slamdata"                %% "matryoshka-core"   % "0.18.3"
  ),

  parallelExecution in Test := false,

  shellPrompt := { s => Project.extract(s).currentProject.id + " > " }
)

lazy val root = Project("root", file(".")).aggregate(core)

lazy val core = Project("core", file("core")).
  settings(commonSettings: _*)
