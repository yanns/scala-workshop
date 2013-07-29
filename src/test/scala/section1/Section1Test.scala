package section1

import org.scalatest.FunSuite

class Section1Test extends FunSuite {

  test("Section1 greets the world") {
    assert(Section1.message === "Hello, World")
  }

  test("Section1 wants to be alone") {
    assert(Section1.greeting === "Hello, World")
    //Section1.greeting = "Leave me alone, world!"
    assert(Section1.greeting === "Leave me alone, world!")
  }

  test("Section1 can calculate the max of 2 numbers") {
    assert(Section1.max(0, 3) === 3)
    assert(Section1.max(0, -3) === 0)
  }
}
