package section2

import org.scalatest.FunSuite

class Section2Spec extends FunSuite {

  test("Section2 concatenates Strings together") {
    val result = Section2.concatenate(Array("Scala", "is", "fun"))
    assert(result === "Scala is fun")
  }

  test("Section2 extract words from list from Strings") {
    val result = Section2.extractWords(Seq("Scala is fun", "fun is good"))
    assert(result === Seq("Scala", "is", "fun", "fun", "is", "good"))
  }

  test("Section2 counts number of occurrences of words") {
    val result = Section2.counts(Seq("Scala is fun", "Scala is even more is", "fun is more", "more is less"))
    assert(result === Map("is" -> 5, "more" -> 3, "less" -> 1, "Scala" -> 2, "even" -> 1, "fun" -> 2))
  }

}
