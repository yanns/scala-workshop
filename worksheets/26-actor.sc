// does not work in worksheet, see section7.ActorApp

import akka.actor.{Props, Actor, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

import scala.concurrent.{Await, Future}

val system = ActorSystem()

class EchoServer extends Actor {
  var nbrOfMessage = 0
  override def receive: Receive = {
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

implicit val timeout: Timeout = 1 second
val result = (echoServer ? "how much?").asInstanceOf[Future[Int]]

println(Await.result(result, 2 seconds))

system.shutdown()
