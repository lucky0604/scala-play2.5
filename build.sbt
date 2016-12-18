name := "playApp"

version := "1.0"

lazy val `playapp` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( cache , ws   , specs2 % Test ,"mysql" % "mysql-connector-java" % "5.1.39", "com.typesafe.play" %% "play-slick" % "2.0.0",
"com.typesafe.play" %% "play-slick-evolutions" % "2.0.0")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

// play provides two styles of routers, one expects its actions to be injected,
// the other, legacy style, accesses its actions statically
routesGenerator := InjectedRoutesGenerator