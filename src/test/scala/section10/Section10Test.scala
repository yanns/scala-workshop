package section10

import org.scalatest.{FunSuite, Matchers, GivenWhenThen, FeatureSpec}
import section7.Section7._
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.duration.DurationInt

class Section10Test extends FunSuite with Matchers {

  test("wordsWithoutEmptyString") {
    Section10.wordsWithoutEmptyString should have size 292
  }

  test("dictionary") {
    Section10.dictionary should contain key 'e'
    Section10.dictionary('e') should contain ("elegant")
  }


}
