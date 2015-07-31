package section9

import section4.Section4.Customer

object Section9Scala extends App {
  val customer = Customer(24, true)
  println(customer)

  println("customer age: " + customer.age)
  println("customer have id: " + customer.haveId)

  println("customer age: " + customer.getAge())
  println("customer have id: " + customer.isHaveId())
}
