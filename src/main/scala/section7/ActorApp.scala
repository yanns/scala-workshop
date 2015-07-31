package section7

import akka.actor.{Props, Actor, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

import scala.concurrent.{Await, Future}

object ActorApp extends App {

  val system = ActorSystem()

  class EchoServer extends Actor {
    var nbrOfMessage = 0
    def receive = {
      case "how much?" => sender() ! nbrOfMessage
      case msg: String =>
        nbrOfMessage += 1
        println(s"[$nbrOfMessage] echo $msg")
    }
  }

  val echoServer = system.actorOf(Props[EchoServer])
  echoServer ! "hi"
  echoServer ! "wie geht's"
  echoServer ! "wie geht's"
  echoServer ! "wie geht's"
  echoServer ! "wie geht's"

  implicit val timeout: Timeout = 1.second
  val result = (echoServer ? "how much?").asInstanceOf[Future[Int]]

  println(Await.result(result, 2.seconds))

  system.shutdown()
}
