enablePlugins(ScalaJSPlugin)

name := "app-client"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.github.japgolly.scalajs-react" %%% "core" % "0.10.1",
  "com.github.japgolly.scalajs-react" %%% "extra" % "0.10.1",
  "com.lihaoyi" %%% "upickle" % "0.3.6",
  "com.github.japgolly.fork.scalaz" %%% "scalaz-core" % "7.1.3"
)

jsDependencies ++= Seq(
  "org.webjars.npm" % "react" % "0.14.2" / "react-with-addons.js" commonJSName "React" minified "react-with-addons.min.js",
  "org.webjars.npm" % "react-dom" % "0.14.2" / "react-dom.js" commonJSName "ReactDOM" minified "react-dom.min.js" dependsOn "react-with-addons.js",
  "org.webjars" % "ratchet" % "2.0.2" / "ratchet.js" minified "ratchet.min.js"
)

ivyScala := ivyScala.value map (_.copy(overrideScalaVersion = true))
incOptions := incOptions.value.withNameHashing(nameHashing = true)
doc in Compile <<= target.map(_ / "none")
sources in(Compile, doc) := Seq.empty
publishArtifact in(Compile, packageDoc) := false


lazy val fastBuild = taskKey[Unit]("Fast js build")
fastBuild.in(Compile) := {
  "./fast-build.sh" !
}
fastBuild.in(Compile) <<= fastBuild.in(Compile).dependsOn(fastOptJS.in(Compile))


lazy val build = taskKey[Unit]("Prepare a production js build")
build.in(Compile) := {
  "./build.sh" !
}
build.in(Compile) <<= build.in(Compile).dependsOn(clean, fullOptJS.in(Compile))
