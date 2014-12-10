package section4

import org.scalatest.{Matchers, FunSuite}
import section4.Section4._

class Section4Test extends FunSuite with Matchers {

  test("a customer under 18 is not allowed to buy cigarettes") {
    intercept[UnderAgeException] {
      new Store().buyCigarettes(Customer(15))
    }
  }

  test("a customer not allowed to buy cigarettes should be informed why") {
    val thrown = the [UnderAgeException] thrownBy {
      new Store().buyCigarettes(Customer(15))
    }
    thrown.getMessage shouldEqual "Customer must be older than 18 but was 15"
  }

  test("a customer with 18 is allowed to buy cigarettes if he can show an ID") {
    new Store().buyCigarettes(Customer(18, haveId = true)) shouldBe a [Cigarettes]
  }

  test("a customer with 18 is not allowed to buy cigarettes if he cannot show an ID") {
    intercept[NoIdException] {
      new Store().buyCigarettes(Customer(18, haveId = false))
    }
  }

  test("a customer's age must be positive") {
    intercept[IllegalArgumentException] {
      Customer(-2)
    }
  }

  test("a customer under 18 is not allowed to buy cigarettes, even in a laxist store") {
    intercept[UnderAgeException] {
      new LaxistStore().buyCigarettes(Customer(15))
    }
  }

  test("a customer over 18 is allowed to buy cigarettes, even without ID in a laxist store") {
    new LaxistStore().buyCigarettes(Customer(18, haveId = false)) shouldBe a [Cigarettes]
  }


}
