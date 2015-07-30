import scala.util.{Failure, Success, Try}

def parseInt(s: String): Try[Int] = Try(s.toInt)

parseInt("123")
parseInt("abc")

// default value
parseInt("abc").getOrElse(0)


parseInt("123").map(_ * 2)

def divideBy(input: Int, divisor: Int): Try[Int] =
  Try(input / divisor)

divideBy(3, 0)
divideBy(3, 1)

val result = parseInt("34").flatMap(i => divideBy(i, 3))
result

for {
  input <- parseInt("34")
  divide <- parseInt("abc")
  result <- divideBy(input, divide)
} yield result

parseInt("457f") match {
  case Success(value) => println(value)
  case Failure(e) => println(s"there was an error: ${e.getMessage}")
}

divideBy(3, 0).recover {
  case e: ArithmeticException => -1
}