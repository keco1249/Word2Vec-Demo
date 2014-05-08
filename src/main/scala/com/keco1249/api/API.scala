package com.keco1249.api

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import akka.cluster.Cluster
import akka.actor.Actor
import akka.actor.Props
import spray.routing.HttpService
import akka.io.IO
import spray.can.Http
import com.keco1249.word2vec.Word2VecActor
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.ActorRef
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.databind.DeserializationFeature

object API extends App {
  
  // Create ActorSystem on port 2551
  implicit val system = ActorSystem("Word2Vec-System")
  
  val apiService = system.actorOf(Props(classOf[APIActor]),
   	  name = "APIServiceActor")
  
  IO(Http) ! Http.Bind(apiService, interface = "0.0.0.0", port = 8080)
}

trait RestAPIService extends HttpService {
  
  val word2Vec:ActorRef
  
  val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  
  val route = {
    path(""){
      get{
        complete{
          "Word2Vec-Demo"
        }
      }
    }~
    (path("distance") & get){
      parameters("word"){ word =>
        complete{
         word2Vec.ask(word)(5.seconds).mapTo[List[(String,Float)]] map{
           results => 
             mapper.writeValueAsString(results)
         }
        }
      }
    }
  }
}

class APIActor extends Actor with RestAPIService{

  implicit val actorRefFactory = context
  
  val word2Vec = actorRefFactory.actorOf(Props(classOf[Word2VecActor]))
  
  def receive = runRoute(route)
}