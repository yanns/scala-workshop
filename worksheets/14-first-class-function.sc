import java.io.{FileReader, BufferedReader, File}

def square(a: Int) = a * a
square(2)

val squareFunction = (a: Int) => a * a
// ==
val squareFunction2 = new Function1[Int, Int] {
  override def apply(a: Int): Int = a * a
}

squareFunction2.apply(2)
squareFunction2(2)
squareFunction(2)

List(1, 3, 4).map(squareFunction)
List(1, 3, 4).map(square)

def addOne(f: Int => Int, arg: Int) = f(arg) + 1
addOne(square, 5)

def withFile(file: File, f: Iterator[String] => Unit) = {
  import collection.JavaConversions.asScalaIterator
  val br = new BufferedReader(new FileReader(file))
  try {
    f(asScalaIterator(br.lines().iterator()))
  } finally {
    br.close()
  }
}

withFile(new File("/Users/yannsimon/projects/scala-workshop/build.sbt"),
  it => it.foreach(println(_)))

