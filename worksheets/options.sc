/**
 * Created with IntelliJ IDEA.
 * User: erche
 * Date: 16.10.13
 * Time: 12:03
 * To change this template use File | Settings | File Templates.
 */

case class User(
  name: String,
  gender: Option[String] = None      )
{
  def completeGender: Option[String] = {
    gender.map {
      g =>

    }
  }
}




val johanna = User("Johanna")


johanna.gender



val bob = User("Bob", Some("M"))


bob.gender



johanna.gender.getOrElse("empty")


bob.gender.getOrElse("empty")


def isMale(user: User): Boolean = {
  user.gender match {
    case Some(g) if g == "M" => true
    case _ => false
  }
}



isMale(bob)


isMale(johanna)

