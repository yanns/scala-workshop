package section3

import org.scalatest.FunSuite

class Section3Test extends FunSuite {

  test("a male user is male") {
    assert(Section3.isMale(1) === true)
  }

  test("an user without known gender is not male") {
    assert(Section3.isMale(2) === false)
  }

  test("a female user is not male ") {
    assert(Section3.isMale(3) === false)
  }

  test("a not found user is not male") {
    assert(Section3.isMale(4) === false)
  }

  test("male user is not allowed") {
    assert(Section3.isUserAllowed(1) === false)
  }

  test("user without known gender is not allowed") {
    assert(Section3.isUserAllowed(2) === false)
  }

  test("female user with unknown age is not allowed") {
    assert(Section3.isUserAllowed(3) === false)
  }

  test("female user with age < 18 is not allowed") {
    assert(Section3.isUserAllowed(4) === false)
  }

  test("female user with age >= 18 is allowed") {
    assert(Section3.isUserAllowed(5) === true)
  }

}
