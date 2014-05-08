name := "Word2Vec-Demo"

version := "0.1"

scalaVersion := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "spray repo" at "http://repo.spray.io/"

resolvers += "jlangdetect-googlecode" at "https://jlangdetect.googlecode.com/svn/repo"

atmosSettings

traceAkka("2.2.3")

libraryDependencies += "org.joda" % "joda-convert" % "1.6"

libraryDependencies += "com.typesafe" % "config" % "1.0.2"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.2.3"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.2.3"

libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.2.3"

libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.2.5"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.3.3"

libraryDependencies += "io.spray" %%  "spray-json" % "1.2.5"

libraryDependencies += "commons-lang" % "commons-lang" % "2.6"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.2.5"

libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.2.5"

libraryDependencies += "play" % "play_2.10" % "2.1.0"

libraryDependencies +=  "redis.clients" % "jedis" % "2.4.1"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.13"

libraryDependencies += "ch.qos.logback" % "logback-core"  % "1.0.13"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.5"

libraryDependencies += "commons-io" % "commons-io" % "2.0.1"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.1.2"

libraryDependencies ++= {
  val akkaV = "2.3.0"
  val sprayV = "1.3.0"
  Seq(
    "io.spray"            %   "spray-can"     % sprayV,
    "io.spray"            %   "spray-routing" % sprayV,
    "io.spray"            %   "spray-caching" % sprayV,
    "io.spray"			  %	  "spray-testkit" % sprayV,
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV,
    "com.typesafe.akka"   %%  "akka-kernel" % akkaV,
    "com.typesafe.akka"   %%  "akka-cluster" % akkaV,
    "com.typesafe.akka"   %%  "akka-remote" % akkaV,
    "com.typesafe.akka"   %%  "akka-slf4j" % akkaV
  )
}

testOptions in Test += Tests.Argument("console")


seq(Revolver.settings: _*)

