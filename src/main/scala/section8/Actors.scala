package section8

import akka.actor.{Actor, ActorSystem, Props}


class EchoServer extends Actor {
  var nbrOfMessages = 0

  def receive = {
    case msg: String =>
      nbrOfMessages += 1
      println(s"echo $msg [$nbrOfMessages]")
  }
}

object Actors extends App {

  val system = ActorSystem()
  println("hello actors!")

  val echoServer = system.actorOf(Props[EchoServer])
  echoServer ! "hi"
  echoServer ! "hallo"
  echoServer ! 42

  system.terminate()
}
