import java.text.SimpleDateFormat
import java.util.Date

object MyImplicits {
  import scala.language.implicitConversions
  implicit def strToDate(str: String): Date =
    new SimpleDateFormat("yyyy-MM-dd").parse(str)
}

import MyImplicits.strToDate

strToDate("2015-07-30").getTime / 1000l

"2015-07-30".getTime / 1000l


// pimp my library
"123".toInt

object Model {
  case class Person(name: String)
  object Person {
    import scala.language.implicitConversions

    implicit def stringToPerson(s: String) = Person(s)
  }
}

def callPerson(p: Model.Person) =
  println(s"hello ${p.name}")

callPerson(Model.Person("Mike"))
callPerson("Mike")
