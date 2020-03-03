name := "third-style-of-param-passing"

version := "1.1"

scalaVersion := "2.13.1"

sbtVersion := "1.3.8"

libraryDependencies ++= Seq("specs2-core", "specs2-mock").flatMap { project =>
  Seq("org.specs2" %% project % "4.8.3" % "test")
}
