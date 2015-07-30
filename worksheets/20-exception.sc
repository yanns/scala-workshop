case class MyException(message: String)
  extends Exception(message)

@throws(classOf[MyException])
def dangerousCall(test: Int): Int =
  test match {
    case 0 => throw MyException("do not use 0!")
    case 1 => throw new RuntimeException("do not use 1!")
    case v => v
  }

dangerousCall(2)


val result = try {
  dangerousCall(0)
} catch {
  case e: MyException =>
    println("catch it!")
    -1
}
result

val myExceptionHandling: PartialFunction[Throwable, Int] = {
  case e: MyException =>
    println("catch it!")
    -1
}

val globalExceptionHandling: PartialFunction[Throwable, Int] = {
  case t: Throwable =>
    println("catch throwable")
    - 10
}

val result2 = try {
  dangerousCall(1)
} catch myExceptionHandling.orElse(globalExceptionHandling)
result2

myExceptionHandling.isDefinedAt(new RuntimeException("test"))
myExceptionHandling.isDefinedAt(new MyException("test"))
myExceptionHandling.lift.apply(MyException("test"))
myExceptionHandling.lift.apply(new RuntimeException("test"))

val allHandling = myExceptionHandling.orElse(globalExceptionHandling)
allHandling.isDefinedAt(new RuntimeException("test"))
allHandling.isDefinedAt(new MyException("test"))
