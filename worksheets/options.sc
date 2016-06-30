import java.util.Date

import org.joda.time.{DateTime, Years}

case class User(
  name: String,
  gender: Option[String] = None,
  dateOfBirth: Option[Date] = None
) {

  def age1: Option[Int] =
    dateOfBirth match {
      case Some(dob) ⇒ Some(calculateAge(dob))
      case None ⇒ None
    }

  def age: Option[Int] =
    dateOfBirth.map(calculateAge)

  private def calculateAge(dateOfBirth: Date): Int = {
    val dob = new DateTime(dateOfBirth)
    val now = new DateTime()
    Years.yearsBetween(dob, now).getYears
  }
}

val johana = User("Johana")
johana
johana.gender
johana.age

"Gender: " + johana.gender.getOrElse("not specified")


val bob = User(
  "bob",
  gender = None,
  dateOfBirth = Some(new DateTime().minusYears(25).toDate))

bob.age
