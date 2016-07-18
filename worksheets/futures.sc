import section7.Section7._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

val boiledWater = boilWater(Water(temperature = 25))

// callbacks
boiledWater.onSuccess { case water =>
  println(water)
}

val pasta = cookPasta("spaghetti", Water(temperature = 80))

pasta.onComplete(t => t match {
  case Success(p) => println(s"success with $p")
  case Failure(e) => println(s"failure with $e")
})
// same as

pasta.onComplete {
  case Success(p) => println(s"success with $p")
  case Failure(e) => println(s"failure with $e")
}

// composition

val pasta2 = boiledWater.flatMap { water =>
  cookPasta("spaghetti", water)
}

// for comprehension
val pasta3 = for {
  water <- boiledWater
  pasta <- cookPasta("spaghetti", water)
} yield pasta


import scala.async.Async.{async, await}
val pasta4 = async {
  val water = await(boilWater(Water(temperature = 25)))
  val pasta = await(cookPasta("spaghetti", water))
  pasta
}


// only for test
import scala.concurrent.Await
import scala.concurrent.duration._

val atMost: Duration = 30.seconds
Await.result(pasta4, atMost)
