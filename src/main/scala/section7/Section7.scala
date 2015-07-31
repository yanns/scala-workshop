package section7

import scala.concurrent.{Promise, Future}
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global

object Section7 {
  type Pasta = String
  type BoiledPasta = String
  type Sauce = String
  type SpaghettiBolognese = String
  case class Water(temperature: Int)

  def boilWater(water: Water): Future[Water] = Future {
    println("preparing to boil " + water)
    Thread.sleep(Random.nextInt(2000))
    println("water is boiled")
    water.copy(temperature = 90)
  }

  def cookPasta(pasta: Pasta, water: Water): Future[BoiledPasta] = Future {
    println(s"preparing to cook pasta $pasta with $water")
    Thread.sleep(Random.nextInt(2000))
    if (water.temperature < 70) throw new Exception("the water is not hot enough")
    println(s"$pasta is ready")
    s"$pasta ready"
  }


  def prepareSauce(ingredients: String*): Future[Sauce] = {
    val result = Promise[Sauce]()
    new Thread(new Runnable {
      override def run(): Unit = {
        println("preparing sauce with ingredients: " + ingredients.mkString(", "))
        Thread.sleep(Random.nextInt(2000))
        if (!ingredients.contains("tomatoes") || !ingredients.contains("beef"))
          result.failure(new Exception("cannot prepare sauce without tomatoes and beef"))
        println("sauce is ready")
        result.success("bolognese sauce")
      }
    }).start()
    result.future
  }

  def prepareSauce1(ingredients: String*): Future[Sauce] = Future {
    println("preparing sauce with ingredients: " + ingredients.mkString(", "))
    Thread.sleep(Random.nextInt(2000))
    if (!ingredients.contains("tomatoes") || !ingredients.contains("beef"))
      throw new Exception("cannot prepare sauce without tomatoes and beef")
    println("sauce is ready")
    "bolognese sauce"
  }

  def mixPastaAndSauce(boiledPasta: BoiledPasta, sauce: Sauce): Future[SpaghettiBolognese] = Future {
    println(s"preparing to mix $boiledPasta with $sauce")
    Thread.sleep(Random.nextInt(1000))
    println(s"mix $boiledPasta with $sauce is ready")
    "spaghetti bolognese ready"
  }

  def prepareSpaghettiBolognese1(pasta: Pasta, water: Water, ingredients: String*): Future[SpaghettiBolognese] = {
    import scala.async.Async._
    val food = async {
      val boiledWater = await(boilWater(water))
      val preparedSauce = await(prepareSauce(ingredients: _*))
      val boiledPasta = await(cookPasta(pasta, boiledWater))
      val mix = await(mixPastaAndSauce(boiledPasta, preparedSauce))
      mix
    }
    food
  }

  def prepareSpaghettiBolognese(pasta: Pasta, water: Water, ingredients: String*): Future[SpaghettiBolognese] = {
    val boiledWater = boilWater(water)
    val sauce = prepareSauce(ingredients: _*)
    for {
      water <- boiledWater
      cookedPasta <- cookPasta(pasta, water)
      preparedSauce <- sauce
      spaghetti <- mixPastaAndSauce(cookedPasta, preparedSauce)
    } yield spaghetti
  }
}
