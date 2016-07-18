package section7

import scala.concurrent.Future
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

  def prepareSauce(ingredients: String*): Future[Sauce] = Future {
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

  def prepareSpaghettiBolognese1(pasta: Pasta, water: Water, ingredients: String*): Future[SpaghettiBolognese] = for {
    boiledWater <- boilWater(water)
    boiledPasta <- cookPasta(pasta, boiledWater)
    sauce <- prepareSauce(ingredients:_*)
    spaghettiBolognese <- mixPastaAndSauce(boiledPasta, sauce)
  } yield spaghettiBolognese


  def prepareSpaghettiBolognese2(pasta: Pasta, water: Water, ingredients: String*): Future[SpaghettiBolognese] =
    for {
      sc <- prepareSauce(ingredients:_*)
      wtr <- boilWater(water)
      psta <- cookPasta(pasta, wtr)
      bolognese <- mixPastaAndSauce(psta, sc)
    } yield bolognese

  def prepareSpaghettiBolognese(pasta: Pasta, water: Water, ingredients: String*): Future[SpaghettiBolognese] = {
    val futureSauce = prepareSauce(ingredients: _*)
    val futureBoiledWater = boilWater(water)
    for {
      sc <- futureSauce
      wtr <- futureBoiledWater
      psta <- cookPasta(pasta, wtr)
      bolognese <- mixPastaAndSauce(psta, sc)
    } yield bolognese
  }

  import scala.async.Async.{async, await}
  def prepareSpaghettiBolognese3(pasta: Pasta, water: Water, ingredients: String*): Future[SpaghettiBolognese] = {
    val spaghetti = async {
      val boiledWater = await(boilWater(water))
      val sauce = await(prepareSauce(ingredients:_*))
      val cookedPasta = await(cookPasta(pasta, boiledWater))
      val readyPasta = await(mixPastaAndSauce(cookedPasta, sauce))
      readyPasta
    }
    spaghetti
  }
}
