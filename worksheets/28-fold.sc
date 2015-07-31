val l = (1 to 10).toList

l.reduce((a, b) => a + b)
l.reduceLeft (_ + _)
l.reduceRight(_ + _)

var acc = 5
for (e <- l) {
  acc = acc + e
}
acc

l.fold(5)((acc, e) => acc + e)
l.foldLeft(5)((acc, e) => acc + e)

val initial = ""
l.foldLeft(initial)((acc, e) => acc + e.toString)
l.foldLeft("")(_ + _)

def divide(x: Double, y: Double) = {
  val result = x / y
  println(s"divided $x by $y to yield $result")
  result
}

val a = Array(1.0, 2.0, 3.0)
a.reduceLeft(divide)
a.reduceRight(divide)
a.reduce(divide)

val v = (1 to 1000000).view
val v2 = v.map { i =>
  println(s"multiply $i by 2")
  i * 2
}
v2.take(6).toList

val fibs: Stream[BigInt] =
  BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map { n =>
    println(s"add ${n._1} with ${n._2}")
    n._1 + n._2
  }

fibs.take(4).toList
fibs.take(8).toList