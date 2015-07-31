package section2

import scala.annotation.tailrec
import scala.collection.mutable

object Section2 {

  def toUpper(s: String) = s.toUpperCase
  val toUpperFunction: String => String = (s: String) => s.toUpperCase
  val toUpperFunction2: (String) => String = (s: String) => s.toUpperCase
  val toUpperFunction3: Function1[String, String] = (s: String) => s.toUpperCase

  // Arrays

  def loud1(input: Array[String]): Array[String] =
    input.map(s => s.toUpperCase)

  def loud(input: Array[String]): Array[String] =
    input.map(toUpperCase)

  def concatenate1(input: Array[String]): String = {
    var toReturn = ""
    for (element <- input) {
      toReturn += element + " "
    }
    toReturn.trim
  }

  def concatenate2(input: Array[String]): String =
    input.mkString(" ")


  def concatenate(input: Array[String]): String = {
    input.length match {
      case 0 => ""
      case 1 => input(0)
      case _ => input(0) + " " + concatenate(input.drop(1))
    }
  }

  // Seq and List

  def loud1(input: Seq[String]): Seq[String] =
    input.map(_.toUpperCase())

  def loud(input: Seq[String]): Seq[String] =
    input.map(toUpper)

  def extractWords1(input: Seq[String]): Seq[String] = {
    val buffer = new mutable.ListBuffer[String]()
    for (s <- input) {
      for (x <- s.split(" ")) {
        buffer += x
      }
    }
    buffer
  }

  def extractWords3(input: Seq[String]): Seq[String] = {
    val buffer = new mutable.ListBuffer[String]()
    for (s <- input ; x <- s.split(" ")) {
      buffer += x
    }
    buffer
  }

  def extractWords4(input: Seq[String]): Seq[String] =
    (for (s <- input) yield s.split(" ")).flatten

  def extractWords5(input: Seq[String]): Seq[String] =
    for (s <- input ; x <- s.split(" ")) yield x

  def extractWords5b(input: Seq[String]): Seq[String] =
    for {
      s <- input
      x <- s.split(" ")
    } yield x

  def extractWords6(input: Seq[String]): Seq[String] =
    input.map(_.split(" ")).flatten

  def extractWords7(input: Seq[String]): Seq[String] =
    input.flatMap(_.split(" "))

  // recursive
  def extractWords8(input: Seq[String]): Seq[String] =
    if (input.isEmpty) input
    else input.head.split(" ") ++ extractWords8(input.tail)

  def extractWords9(input: Seq[String]): Seq[String] =
    input match {
      case Nil => Nil
      case head +: tail => head.split(" ") ++ extractWords9(tail)
    }

  // tail-recursive
  def extractWords10(input: Seq[String]): Seq[String] = {
    @tailrec
    def extract(result: Seq[String], list: Seq[String]): Seq[String] =
      list match {
        case Nil => result
        case head +: tail => extract(result ++ head.split(" "), tail)
      }
    extract(Nil, input)
  }

  def extractWords(input: Seq[String]): Seq[String] =
//    input.foldLeft(Seq.empty[String])((acc, e) => acc ++ e.split(" "))
//    input.foldLeft(Seq.empty[String])(_ ++ _.split(" "))
    (Seq.empty[String] /: input) (_ ++ _.split(" "))

  // Map

  def loud1(input: Map[String, String]): Map[String, String] = {
    var map = Map[String, String]()
    for ((k,v) <- input)
      map += k.toUpperCase -> v.toUpperCase()
    map
  }

  def loud2(input: Map[String, String]): Map[String, String] =
    for ((k,v) <- input) yield
      k.toUpperCase -> v.toUpperCase()

  def loud3(input: Map[String, String]): Map[String, String] =
    input.map(kv => kv._1.toUpperCase -> kv._2.toUpperCase)

  def loud(input: Map[String, String]): Map[String, String] =
    input.map { case (k,v) => k.toUpperCase -> v.toUpperCase }

  def counts1(input: Seq[String]): Map[String, Int] = {
    val map = scala.collection.mutable.HashMap.empty[String, Int]
    for (word <- extractWords(input)) {
      val n = map.getOrElse(word, 0)
      map += word -> (n + 1)
    }
    map.toMap
  }

  def counts2(input: Seq[String]): Map[String, Int] = {
    val words = extractWords(input)
    var toReturn = new mutable.HashMap[String, Int]
    for (word <- words) {
      toReturn += word -> 0
    }
    words.foreach({s => toReturn.apply(s) += 1})
    toReturn.toMap
  }

  def counts3(input: Seq[String]): Map[String, Int] = {
    val words = extractWords(input)
    words.groupBy(l => l).map(t => (t._1, t._2.length))
  }

  def counts4(input: Seq[String]): Map[String, Int] =
    extractWords(input)
      .groupBy(identity)
      .mapValues(t => t.length)

  def counts(input: Seq[String]): Map[String, Int] =
    extractWords(input).foldLeft(Map.empty[String, Int]) { (map, word) =>
      map + (word -> (map.getOrElse(word, 0) + 1))
    }

  val tries = mutable.HashMap[Char, Int]()

  def addTryFor1(c: Char): Unit = {
    if (!tries.contains(c))
      tries += c -> 1
    else
      tries.update(c, tries.apply(c) + 1)
  }

  def addTryFor2(c: Char): Unit =
    tries.put(c, tries.getOrElse(c, 0) + 1)

  def addTryFor(c: Char): Unit =
    tries(c) = tries.getOrElse(c, 0) + 1

  def howManyTries1(c: Char): Int =
    if (!tries.contains(c)) 0
    else tries.apply(c)

  def howManyTries(c: Char): Int =
    tries.getOrElse(c, 0)

}
