package section2

import org.scalatest.FunSuite

class Section2Spec extends FunSuite {

  // Arrays

  test("Section2 can speak very loud") {
    val result = Section2.loud(Array("Hi", "there"))
    assert(result === Array("HI", "THERE"))
  }

  test("Section2 concatenates Strings together") {
    val result = Section2.concatenate(Array("Scala", "is", "fun"))
    assert(result === "Scala is fun")
  }

  // Seq and List

  test("Section2 can speak very loud with Seq") {
    val result = Section2.loud(Seq("Hi", "there"))
    assert(result === Seq("HI", "THERE"))
  }

  test("Section2 extract words from list from Strings") {
    val result = Section2.extractWords(Seq("Scala is fun", "fun is good"))
    assert(result === Seq("Scala", "is", "fun", "fun", "is", "good"))
  }

  // Map

  test("Section2 can speak very loud with Map") {
    val result = Section2.loud(Map("Hi" -> "there", "hello" -> "world"))
    assert(result === Map("HI" -> "THERE", "HELLO" -> "WORLD"))
  }

  test("Section2 counts number of occurrences of words") {
    val result = Section2.counts(Seq("Scala is fun", "Scala is even more is", "fun is more", "more is less"))
    assert(result === Map("is" -> 5, "more" -> 3, "less" -> 1, "Scala" -> 2, "even" -> 1, "fun" -> 2))
  }

  test("Section2 can remember which character was tried for which word") {
    assert(Section2.isCharacterAlreadyTried(2, 'c') === false)
    Section2.addTriedCharacter(2, 'c')
    assert(Section2.isCharacterAlreadyTried(2, 'c') === true)
    assert(Section2.isCharacterAlreadyTried(2, 'a') === false)
    assert(Section2.isCharacterAlreadyTried(1, 'c') === false)
  }

}
