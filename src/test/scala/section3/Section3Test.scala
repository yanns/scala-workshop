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

}
