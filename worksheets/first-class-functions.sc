import java.io.{BufferedReader, File, FileReader}

def square(a: Int): Int = a * a

square(5)


val squareFunction1: Function1[Int, Int] = new Function[Int, Int] {
  override def apply(a: Int): Int = a * a
}

squareFunction1(5)

val squareFunction: (Int) => Int = (a: Int) => a * a
squareFunction(5)

val squareFunction2 = (a: Int) => a * a
squareFunction2(5)


def useFunctionAndAdd1(f: Int ⇒ Int, arg: Int): Int =
  f(arg) + 1


useFunctionAndAdd1(squareFunction, 5)
useFunctionAndAdd1(square, 5)


def withFile(file: File, f: Iterator[String] ⇒ Unit) = {
  import collection.JavaConversions.asScalaIterator

  val br = new BufferedReader(new FileReader(file))
  try {
    f(asScalaIterator(br.lines().iterator()))
  } finally {
    br.close()
  }
}

withFile(new File("/Users/yannsimon/projects/scala-workshop/build.sbt"),
  it ⇒ it.foreach(println))