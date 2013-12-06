package section8

import akka.actor._
import scala.concurrent.duration.DurationInt
import scala.concurrent.ExecutionContext.Implicits.global
import section7.Section7
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.Await
import section7.Section7.Water
import scala.util.Random

object Section8 extends App {

  val system = ActorSystem("ItalianRestaurant")
  val italianRestaurant = system.actorOf(Props[ItalianRestaurant], "ItalianRestaurant")
  val alice = system.actorOf(Props(classOf[Customer], italianRestaurant), "alice")
  italianRestaurant.tell(ItalianRestaurant.CustomerWantsTable, alice)

  Thread.sleep(4000)

  val bob = system.actorOf(Props(classOf[Customer], italianRestaurant), "bob")
  italianRestaurant.tell(ItalianRestaurant.CustomerWantsTable, bob)

  val jack = system.actorOf(Props(classOf[Customer], italianRestaurant), "jack")
  italianRestaurant.tell(ItalianRestaurant.CustomerWantsTable, jack)

  val cinderella = system.actorOf(Props(classOf[Customer], italianRestaurant), "cinderella")
  italianRestaurant.tell(ItalianRestaurant.CustomerWantsTable, cinderella)

  val hulk = system.actorOf(Props(classOf[Customer], italianRestaurant), "hulk")
  italianRestaurant.tell(ItalianRestaurant.CustomerWantsTable, hulk)

  Thread.sleep(5000)

  implicit val timeout = Timeout(10.seconds)
  val isClosed = italianRestaurant ? ItalianRestaurant.ClosingTime
  Await.ready(isClosed, 10.seconds)
  system.shutdown()
}


object ItalianRestaurant {
  case object CustomerWantsTable
  case object CustomerLeave

  sealed trait CustomerCommand
  case object PastaCommand extends CustomerCommand
  case object SpaghettiBolognese extends CustomerCommand

  case object ClosingTime
}

class ItalianRestaurant extends Actor {
  import ItalianRestaurant._

  val totalNumberOfTables = 3
  var tablesFree = totalNumberOfTables
  var closing = false

  def receive = {

    case CustomerWantsTable if closing => sender ! Customer.RestaurantWillClose
    case CustomerWantsTable if tablesFree > 0 =>
      sender ! Customer.TableFree
      tablesFree = tablesFree -1
    case CustomerWantsTable => sender ! Customer.HaveToWait

    case CustomerLeave =>
      println("bye bye " + sender.path.name)
      tablesFree = tablesFree + 1

    case PastaCommand =>
      println("I have to prepare some pasta for " + sender.path.name)
      val requestor = sender
      for {
        water <- Section7.boilWater(Water(temperature = 25))
        pasta <- Section7.cookPasta("pasta", water)
      } {
        println("Here some pasta for " + requestor.path.name)
        requestor ! pasta
      }

    case SpaghettiBolognese =>
      println("PANIC, I DO NOT KNOW THAT TO DO FOR " + sender.path.name)
      sender ! PoisonPill
      self.tell(CustomerLeave, sender)

    case ClosingTime =>
      println(s"time to close, $tablesFree table(s) free")
      closing = true
      if (tablesFree == totalNumberOfTables) {
        sender ! "closed"
        context.stop(self)
      } else {
        val requestor = sender
        context.system.scheduler.scheduleOnce(1.seconds) {
          self.tell(ClosingTime, requestor)
        }
      }

  }
}

object Customer {
  case object TableFree
  case object HaveToWait
  case object RestaurantWillClose
}

class Customer(italianRestaurant: ActorRef) extends Actor {
  import Customer._

  def receive = {
    case HaveToWait =>
      debug("oh no, I have to wait")
      context.system.scheduler.scheduleOnce(2.seconds) {
        italianRestaurant ! ItalianRestaurant.CustomerWantsTable
      }

    case RestaurantWillClose =>
      debug("ok, I'll come back tomorrow")

    case TableFree =>
      debug("thanks for the table")
      if (Random.nextBoolean())
        italianRestaurant ! ItalianRestaurant.PastaCommand
      else
        italianRestaurant ! ItalianRestaurant.SpaghettiBolognese

    case "pasta ready" =>
      debug("thanks for the pasta")
      context.system.scheduler.scheduleOnce(2.seconds) {
        italianRestaurant ! ItalianRestaurant.CustomerLeave
      }

  }

  private def debug(statement: String) = println(self.path.name + ": " + statement)
}
