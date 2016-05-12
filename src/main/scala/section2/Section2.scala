package section2

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

  def loud(input: Map[String, String]): Map[String, String] = ???

  def counts(input: Seq[String]): Map[String, Int] = ???

  def addTryFor(c: Char): Unit = ???
  def howManyTries(c: Char): Int = ???

}
