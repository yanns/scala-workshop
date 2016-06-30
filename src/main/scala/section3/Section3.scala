package section3

object Section3 {

  case class User(
    id: Int,
    firstName: String,
    lastName: String,
    age: Option[Int],
    gender: Option[String])

  object UserRepository {
    private val male = Some("male")
    private val female = Some("female")

    private val users = Map(
      1 -> User(1, "John", "Doe", Some(32), male),
      2 -> User(2, "Johanna", "Doe", Some(30), None),
      3 -> User(3, "Alice", "Doe", None, female),
      4 -> User(4, "Lisa", "Doe", Some(8), female),
      5 -> User(5, "Kathrin", "Doe", Some(18), female)
    )

    def findById(id: Int): Option[User] = users.get(id)

    def findAll = users.values
  }

  def isMale1(id: Int): Boolean = {
    val user = UserRepository.findById(id)
    user match {
      case None => false
      case Some(u) => u.gender match { // not exhaustive
        case Some("male") => true
        case Some("female") => false
        case None => false
      }
    }
  }

  def isMale2(id: Int): Boolean = {
    val user = UserRepository.findById(id)
    user match {
      case None => false
      case Some(u) => u.gender.contains("male")
    }
  }

  def isMale3(id: Int): Boolean =
    UserRepository.findById(id) match {
      case Some(user) => user.gender.exists(g => g == "male")
      case None => false
    }

  def isMale5(id: Int): Boolean =
    UserRepository.findById(id) match {
      case Some(user) => user.gender.getOrElse("") == "male"
      case None => false
    }

  def isMale4(id: Int): Boolean =
    UserRepository.findById(id).exists { user =>
      user.gender match {
        case Some("male") => true
        case _ => false
      }
    }

  def isMale6(id: Int): Boolean =
    UserRepository.findById(id).exists(_.gender.contains("male"))

  def isMale7(id: Int): Boolean =
    UserRepository.findById(id).flatMap { user =>
      user.gender.map { g =>
        g == "male"
      }
    }.getOrElse(false)

  def isMale(id: Int): Boolean = {
    val result = for {
      user <- UserRepository.findById(id)
      gender <- user.gender
      if gender == "male"
    } yield true

    result.getOrElse(false)
  }

  def isUserAllowed1(id: Int): Boolean =
    UserRepository.findById(id).exists(u => u.gender.contains("female") && u.age.exists(_ >= 18))

  def isUserAllowed2(id: Int): Boolean = {
    val result = for {
      user <- UserRepository.findById(id)
      gender <- user.gender
      age <- user.age
    } yield gender == "female" && age >= 18

    result.getOrElse(false)
  }

  def isUserAllowed3(id: Int): Boolean =
    UserRepository.findById(id) match {
      case None => false
      case Some(user) => isFemale(user) && isAdult(user)
    }

  def isUserAllowed(id: Int): Boolean =
    UserRepository.findById(id) match {
      case Some(user) if isFemale(user) && isAdult(user) => true
      case _ => false
    }

  private def isFemale(user: User): Boolean =
    user.gender.contains("female")

  private def isAdult(user: User): Boolean =
    user.age.exists(_ >= 18)

}
