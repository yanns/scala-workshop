package section7

import section7.Section7._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits._
import scala.util.{Success, Failure}
import scala.async.Async.{async, await}


object Main extends App {
  val boiledWater = boilWater(Water(temperature = 25))

  boiledWater.onSuccess({
    case water: Water => println(s"success with $water")
  })

  val pasta = cookPasta("spaghetti", Water(temperature = 20))
  pasta.onComplete({
    case Success(p) => println(s"success with $p")
    case Failure(e) => println(s"failure with $e")
  })

  // composition
  val result = boiledWater.flatMap (bw => cookPasta("spaghetti1", bw))
  result

  val pasta4 = for {
    boiledWater2 <- boilWater(Water(temperature = 25))
    pasta <- cookPasta("spaghetti2", boiledWater2)
  } yield pasta

  // with Scala async project
  val pasta5 = async {
    val boiledWater = await(boilWater(Water(temperature = 25)))
    val pasta = await(cookPasta("spaghetti5", boiledWater))
    pasta
  }

  val atMost: Duration = 30.seconds
  val p = Await.result(pasta5, atMost)
  p

}
