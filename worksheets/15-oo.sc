class SimpleGreeter {
  val greeting = "Hello, world"
  def greet() = println(greeting)
}

val g = new SimpleGreeter
g.greet()

// with parameter
class FancyGreeter(greeting: String) {
  def greet() = println(greeting)
}

val f = new FancyGreeter("Hi")
f.greet()

// with check in constructor
class CarefulGreeter(greeting: String) {
  if (greeting == null)
    throw new NullPointerException("greeting is null")

  def greet() = println(greeting)
}

val c = new CarefulGreeter("null")

// auxiliary constructor
class RepeatGreeter(greeting: String, count: Int) {
  def this(greeting: String) = this(greeting, 1)
  def greet() = {
    for (i <- 1 to count) println(greeting)
  }
}

val r = new RepeatGreeter("hi")
r.greet()

// default value

class RepeatGreeter2(greeting: String, count: Int = 1) {
  def greet() = {
    for (i <- 1 to count) println(greeting)
  }
}

val r2 = new RepeatGreeter2("hi")
r2.greet()

val r3 = new RepeatGreeter2("hi", 5)
r3.greet()

// named parameter
val r4 = new RepeatGreeter2("hi", count = 5)
r4.greet()

val r5 = new RepeatGreeter2(count = 5, greeting = "bye")
r5.greet()
