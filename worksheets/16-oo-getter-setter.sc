class Person(
  val firstName: String,
  val lastName: String,
  var age: Int)

val obama = new Person(
  firstName = "Barack",
  lastName = "Obama",
  age = 51)

obama.firstName
obama.age
obama.age = 52

obama.age

class Person2(val firstName: String, val lastName: String, initialAge: Int) {
  private var _age = initialAge
  def age = _age
  def age_=(newAge: Int) = _age = newAge
}

val obama2 = new Person2(
  firstName = "Barack",
  lastName = "Obama", 50)

obama2.age
obama2.age = 51
obama2.age_=(52)
obama2.age


