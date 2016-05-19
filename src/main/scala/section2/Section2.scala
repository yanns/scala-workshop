package section2

import scala.collection.immutable.HashMap

object Section2 {

  // Arrays

  // imperative style
  def loud2(input: Array[String]): Array[String] = {
    val uppercase = new Array[String](input.length)
    for (i ← 0 to input.length - 1) {
      uppercase(i) = input(i).toUpperCase
    }

    uppercase
  }

  private def myToUpperCase(s: String): String = s.toUpperCase

  def loud(input: Array[String]): Array[String] =
    input.map(s => myToUpperCase(s))
  // same:
//    input.map(_.toUpperCase)


  def concatenate(input: Array[String]): String =
    input.mkString(" ")

  // Seq and List

  def loud(input: Seq[String]): Seq[String] =
    input.map(s => myToUpperCase(s))

  def extractWords1(input: Seq[String]): Seq[String] =
    input.mkString(" ").split(" ")

  def extractWords2(input: Seq[String]): Seq[String] =
    input.map(s => s.split(" ")).flatten
    //same

  def extractWords3(input: Seq[String]): Seq[String] =
    input.flatMap(s => s.split(" "))

  def extractWords(input: Seq[String]): Seq[String] = {
    import scala.collection.mutable

    val result = mutable.ListBuffer[String]()
    input.foreach(s => s.split(" ").foreach(w => result.append(w)))
    result.toSeq
  }

  // Map

  // immutable data structure with var
  def loud1(input: Map[String, String]): Map[String, String] = {
    var upper = new HashMap[String, String]
    input foreach (tuple => {
      upper = upper + (tuple._1.toUpperCase -> tuple._2.toUpperCase)
    })
    upper
  }

  // mutable data structure with val
  def loud2(input: Map[String, String]): Map[String, String] = {
    import scala.collection.mutable
    val upper = new mutable.HashMap[String, String]
    input foreach (tuple => {
      upper(tuple._1.toUpperCase) = tuple._2.toUpperCase
    })
    upper.toMap
  }

  def loud3(input: Map[String, String]): Map[String, String] =
    input.map(a => (a._1.toUpperCase, a._2.toUpperCase))

  def loud(input: Map[String, String]): Map[String, String] =
    input.map { case (k, v) => k.toUpperCase -> v.toUpperCase }

  def counts1(input: Seq[String]): Map[String, Int] = {
    var wordCount = new HashMap[String, Int]
    val extractedWords = extractWords(input)
    for (word <- extractedWords) {
      wordCount = wordCount + (word -> extractedWords.count(_ == word))
    }
    wordCount
  }

  def counts2(input: Seq[String]): Map[String, Int] = {
    import scala.collection.mutable

    val result = new mutable.HashMap[String, Int]()
    input.flatMap(s => s.split(" "))
      .foreach { s =>
        if (result contains s)
          result += (s -> (result(s) + 1))
        else
          result += (s -> 1)
      }
    result.toMap
  }

  def counts3(input: Seq[String]): Map[String, Int] = {
    val words: Seq[String] = extractWords(input)
    val map: Map[String, Seq[String]] = words.groupBy(a => a)
    map.mapValues(v => v.size)
  }

  def counts4(input: Seq[String]): Map[String, Int] =
    input
      .flatMap(a => a.split(" "))
      .groupBy(a => a)
      .mapValues(_.size)

  def counts5(input: Seq[String]): Map[String, Int] =
    extractWords(input)
      .groupBy(identity)
      .mapValues(s => s.length)

  // possible but not recommended
  def counts(input: Seq[String]): Map[String, Int] =
    extractWords(input) groupBy identity mapValues (_.length)

  val map = collection.mutable.HashMap[Char, Int]()
  def addTryFor(c: Char): Unit = {
    map(c) = howManyTries(c) + 1
  }
  def howManyTries1(c: Char): Int =
    if (map.get(c).isDefined)
      map.get(c).get
    else 0

  def howManyTries(c: Char): Int =
    map.getOrElse(c, 0)

}
