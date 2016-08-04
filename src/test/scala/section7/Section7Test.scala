package section7

import org.scalatest.{Matchers, GivenWhenThen, FeatureSpec}
import section7.Section7._
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.duration.DurationInt
import scala.concurrent.ExecutionContext.Implicits.global

class Section7Test extends FeatureSpec with GivenWhenThen with Matchers {

  info("As father alone today with my kids")
  info("I want to cook spaghetti bolognese")
  info("So that all my kids eat a lot")
  info("and quickly go to bed")

  val atMost: Duration = 3.seconds

  feature("Cooking spaghetti bolognese") {
    scenario("A featherbrained forget to boil water") {
      Given("water that is not hot enough")
      val water = Water(temperature = 25)

      When("I try to cook spaghetti")
      val spaghetti = cookPasta("spaghetti", water)

      Then("the pasta is never ready")
      intercept [NotHotEnoughException] {
        Await.result(spaghetti, atMost)
      }
    }

    scenario("I prepare everything sequentially") {
      Given("I have all ingredients")
      val ingredients = Seq("tomatoes", "beef", "onions", "carrots", "celery")

      And("I prepare the sauce")
      val sauce = Await.result(prepareSauce(ingredients:_*), atMost)

      And("I prepare the spaghetti")
      val futureSpaghetti = for {
        water <- boilWater(Water(temperature = 25))
        spaghetti <- cookPasta("spaghetti", water)
      } yield spaghetti
      val spaghetti = Await.result(futureSpaghetti, atMost)

      When("I mix the sauce and the spaghetti")
      val mix = mixPastaAndSauce(spaghetti, sauce)

      Then("the spaghetti bolognese are ready")
      Await.result(mix, atMost) shouldEqual "spaghetti bolognese ready"
    }

    scenario("I prepare the sauce and the spaghetti in parallel") {
      Given("I have all ingredients")
      val ingredients = Seq("tomatoes", "beef", "onions", "carrots", "celery")

      Then("I cook the spaghetti bolognese")
      val mix = prepareSpaghettiBolognese("spaghetti", Water(temperature = 25), ingredients:_*)

      Then("the spaghetti bolognese are ready")
      Await.result(mix, atMost) shouldEqual "spaghetti bolognese ready"
    }
  }


}
