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

    def isMale(gender: Option[String]): Boolean =
      gender == male
  }

  def isMale1(id: Int): Boolean = {
    val user = UserRepository.findById(id)
    user != None && user.get.gender == Some("male")
  }

  def isMale2(id: Int): Boolean = {
    UserRepository.findById(id) match {
      case None => false
      case Some(user) => UserRepository.isMale(user.gender)
    }
  }

  def isMale3(id: Int): Boolean =
    UserRepository.findById(id).exists(_.gender.contains("male"))

  def isMale4(id: Int): Boolean = {
    UserRepository.findById(id) match {
      case None => false
      case Some(user) => user.gender == Some("male")
    }
  }

  def isMale5(id: Int): Boolean = {
    UserRepository.findById(id) match {
      case None => false
      case Some(user) => user.gender match {
        case None => false
        case Some(gender) => gender == "male"
      }
    }
  }

  def isMale6(id: Int): Boolean = {
    UserRepository.findById(id)
      .flatMap (user => user.gender
        .map(gender => gender == "male")).getOrElse(false)
  }

  def isMale(id: Int): Boolean = {
    val result: Option[Boolean] = for {
      user <- UserRepository.findById(id)
      gender <- user.gender
    } yield gender == "male"
    result.getOrElse(false)
  }

  def isUserAllowed1(id: Int): Boolean = {
    UserRepository.findById(id) match {
      case None => false
      case Some(user) => user.gender match {
        case Some("female") => user.age match {
          case None => false
          case Some(age) => age >= 18
        }
        case _ => false
      }
    }
  }

  def isUserAllowed2(id: Int): Boolean = {
    val result: Option[Boolean] = for {
      user <- UserRepository.findById(id)
      gender <- user.gender
      age <- user.age
    } yield gender == "female" && age >= 18
    result.getOrElse(false)
  }

  def isUserAllowed(id: Int): Boolean =
    userAllowed(id).isDefined

  def isFemale(gender: Option[String]) = gender.contains("female")
  def ageAllowed(age: Option[Int]) = age.fold(false)(_ >= 18)

  def userAllowed1(id: Int): Option[User] =
    UserRepository.findById(id).filter(u => isFemale(u.gender) && ageAllowed(u.age))

  def userAllowed3(id: Int): Option[User] =
    UserRepository.findById(id) match {
      case Some(user) if user.gender.contains("female") && user.age.fold(false)(_ >= 18) => Some(user)
      case _ => None
    }

  def userAllowed4(id: Int): Option[User] =
    isUserAllowed(id) match {
      case false => None
      case true => UserRepository.findById(id)
    }

  def userAllowed(id: Int): Option[User] =
    for {
      user <- UserRepository.findById(id)
      gender <- user.gender if gender == "female"
      age <- user.age if age >= 18
    } yield user
}
