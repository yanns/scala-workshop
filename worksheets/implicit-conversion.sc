import java.text.SimpleDateFormat
import java.util.Date

import scala.language.implicitConversions

// real scala - the function is pure
//def strToDate(str: String): Try[Date] =
//  Try(new SimpleDateFormat("yyyy-MM-dd").parse(str))

object Implicits {
  implicit def strToDate(str: String): Date =
    new SimpleDateFormat("yyyy-MM-dd").parse(str)
}

import Implicits._

strToDate("2016-06-06")
strToDate("2016-06-06").getTime / 1000l
"2016-06-06".getTime / 1000l


// implicit scope

object Model {
  case class Person(name: String)
  object Person {
    implicit def stringToPerson(s: String): Model.Person =
      Model.Person(s)
  }
}

def callPerson(person: Model.Person): Unit =
  println(s"hello ${person.name}")



object MyImplicits {
  implicit def stringToPerson(s: String): Model.Person =
    Model.Person(s)
}

val yann = Model.Person("yann")
callPerson(yann)
callPerson("baraa")


// implicit conversions defined in Predef
"abc".head
val i = 12
i + " a"
s"$i a"
"a" + Set("b", "c")
