package section4

import scala.beans.{BooleanBeanProperty, BeanProperty}

object Section4 {

  class Cigarettes
  case class UnderAgeException(message: String) extends Exception(message)
  case class NoIdException(message: String) extends Exception(message)

  case class Customer(@BeanProperty age: Int, @BooleanBeanProperty haveId: Boolean = false) {
    // hint: you can use the require function
    require(age > 0, s"customer have illegal age $age")
  }

  class Store {
    def buyCigarettes(customer: Customer): Cigarettes = {
      if (customer.age < 18)
        throw new UnderAgeException(s"Customer must be older than 18 but was ${customer.age}")
      else if (!customer.haveId)
        throw new NoIdException("no id!")
      else
        new Cigarettes
    }

//    def buyCigarettes(customer: Customer): Cigarettes = customer match {
//      case c if c.age < 18 => throw new UnderAgeException(s"Customer must be older than 18 but was ${customer.age}")
//      ...
//    }
  }

  class LaxistStore extends Store {
    override def buyCigarettes(customer: Customer): Cigarettes =
      try {
        super.buyCigarettes(customer)
      } catch {
        case e: NoIdException => new Cigarettes
      }
  }

}
