package section7

import scala.concurrent.{Future, Promise}
import scala.util.{Random, Try}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal

object Section7 {
  type Pasta = String
  type BoiledPasta = String
  type Sauce = String
  type SpaghettiBolognese = String
  case class Water(temperature: Int)

  case class NotHotEnoughException(msg: String) extends Exception

  def boilWater(water: Water): Future[Water] = Future {
    println("preparing to boil " + water)
    Thread.sleep(Random.nextInt(2000))
    println("water is boiled")
    water.copy(temperature = 90)
  }

  def cookPasta1(pasta: Pasta, water: Water): Future[BoiledPasta] = {
    val p = Promise[BoiledPasta]()
    // complete the promise!
    Future {
      println(s"preparing to cook pasta $pasta with $water")
      Thread.sleep(Random.nextInt(2000))
      if (water.temperature < 90) {
        p.failure(new NotHotEnoughException("the water is not hot enough"))
      }
      println(s"$pasta is ready")
//      s"$pasta ready"
      p.success(s"$pasta ready")
    }
    p.future
  }

  def cookPasta(pasta: Pasta, water: Water): Future[BoiledPasta] = {
    val p = Promise[BoiledPasta]()
    // complete the promise!
    Future {
      val result = Try {
        println(s"preparing to cook pasta $pasta with $water")
        Thread.sleep(Random.nextInt(2000))
        if (water.temperature < 90) throw new NotHotEnoughException("the water is not hot enough")
        println(s"$pasta is ready")
        s"$pasta ready"
      }
      p.complete(result)
    }
    p.future
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

  // with steps in parallel
  def prepareSpaghettiBolognese4(pasta: Pasta, water: Water, ingredients: String*): Future[SpaghettiBolognese] = {
    val sauceFuture = prepareSauce(ingredients:_*)
    val waterFuture = boilWater(water)
    val pastaFuture = for {
      water <- waterFuture
      cookedPasta <- cookPasta(pasta, water)
    } yield cookedPasta
    for {
      pasta <- pastaFuture
      sauce <- sauceFuture
      spaghettiBolognese <- mixPastaAndSauce(pasta, sauce)
    } yield spaghettiBolognese
  }

  def prepareSpaghettiBolognese5(pasta: Pasta, water: Water, ingredients: String*): Future[SpaghettiBolognese] = {
    val futureSause = prepareSauce(ingredients: _*)
    val futureCookedPasta: Future[BoiledPasta] = prepareWaterThenCookPasta(pasta, water)
    for {
      cookedPasta <- futureCookedPasta
      sauce <- futureSause
      spaghettiBolognese <- mixPastaAndSauce(cookedPasta, sauce)
    } yield spaghettiBolognese
  }

  def prepareWaterThenCookPasta(pasta: Pasta, water: Water): Future[BoiledPasta] = {
    val futureCookedPasta = for {
      boiledWater <- boilWater(water)
      boiledPasta <- cookPasta(pasta, boiledWater)
    } yield boiledPasta
    futureCookedPasta
  }

  import scala.async.Async.{async, await}
  def prepareSpaghettiBolognese6(pasta: Pasta, water: Water, ingredients: String*): Future[SpaghettiBolognese] = {
    val sauceFuture = async { await (prepareSauce (ingredients:_*)) }
    val cookedPastaFuture = async {
      val boiledWater = await (boilWater (water))
      await (cookPasta (pasta, boiledWater))
    }
    async {
      val cookedPasta = await (cookedPastaFuture)
      val sauce = await (sauceFuture)
      await (mixPastaAndSauce (cookedPasta, sauce))
    }
  }

  def prepareSpaghettiBolognese(pasta: Pasta, water: Water, ingredients: String*): Future[SpaghettiBolognese] =
    async {
      val preparedSauceTask = prepareSauce(ingredients:_*)
      val cookedPastaTask = boilWater(water).flatMap(boiledWater => cookPasta(pasta, boiledWater))

      val recoverF: PartialFunction[Throwable, Future[String]] = {
        case NonFatal(t) =>
          println(t)
          Future.successful("pasta is ready nevertheless")
      }
      val recovered = try {
        cookedPastaTask.recoverWith(recoverF)
      } catch recoverF

      val preparedSauce = await { preparedSauceTask }
      val cookedPasta = await { recovered }
      await { mixPastaAndSauce(cookedPasta, preparedSauce) }
    }
}
