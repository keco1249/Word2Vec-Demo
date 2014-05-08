package com.keco1249.word2vec

import akka.actor.Actor
import akka.actor.ActorRef

class Word2VecActor extends Actor {

  val model = new Word2Vec()
  
  try{
    model.load("/Users/kevincolin/Development/trunk/train-vectors.bin") // Load training set
  } catch{
    case e:Exception => 
      e.printStackTrace()
  }
  
  override def preStart = println("Word2VecActor initialized")
  
  def receive ={
    case s:String => cosineSim(s, 10, sender)
    case a:Any => println("Word2VecActor: Unknown message received - " + a)
  }
  
  /** 
   */
  def cosineSim(string:String, num:Int, origin:ActorRef):Unit = {

    val top = model.distance(List(string), num)
    origin ! top
  }
  
}