package com.hm

import java.util.concurrent.Executors

import akka.actor.ActorSystem

import scala.concurrent.ExecutionContext

/**
  * Created by hari on 17/2/17.
  */
object ServerActorSystem {


    implicit val system:ActorSystem = ActorSystem()
    implicit val ec:ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))

}
