import java.util.Date

import org.joda.time.{DateTime, Years}

case class User(
  name: String,
  gender: Option[String] = None,
  dateOfBirth: Option[Date] = None
) {
  def age: Option[Int] = dateOfBirth match {
    case None => None
    case Some(dob) => Some(calculateAge(dob))
  }

  def age2: Option[Int] =
    dateOfBirth.map(dob => calculateAge(dob))

  private def calculateAge(dateOfBirth: Date): Int = {
    val dob = new DateTime(dateOfBirth)
    val now = new DateTime()
    Years.yearsBetween(dob, now).getYears
  }
}

val johanna = User("Johanna", Some("F"))
johanna.age

val bob = User("Bob", None, Some(new DateTime().minusYears(25).toDate))
bob.age
bob.age2

johanna.gender.getOrElse("not defined")
bob.gender.getOrElse("not defined")


val fromJava: String = "hello"
Option(fromJava)



