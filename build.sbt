name := "scala_with_cats"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.0.0",
  "org.typelevel" %% "cats-kernel" % "1.0.0"
)

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-Ypartial-unification"
)