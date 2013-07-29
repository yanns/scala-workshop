package section3

object Section3 {

  case class User(
    id: Int,
    firstName: String,
    lastName: String,
    age: Int,
    gender: Option[String])

  object UserRepository {
    private val users = Map(
      1 -> User(1, "John", "Doe", 32, Some("male")),
      2 -> User(2, "Johanna", "Doe", 30, None),
      3 -> User(3, "Alice", "Doe", 30, Some("female"))
    )

    def findById(id: Int): Option[User] = users.get(id)

    def findAll = users.values
  }

  def isMale(id: Int): Boolean = ???

}
