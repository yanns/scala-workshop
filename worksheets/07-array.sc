val greetStrings = new Array[String](3)

greetStrings(1) = ", "
greetStrings(2) = "World"
//greetStrings(3) = "World"

greetStrings(0) = "Hello"
// ==
greetStrings.update(0, "Hi")

greetStrings.foreach(println)

for (i <- 0 until greetStrings.size)
  println(greetStrings(i))

for (i <- 0.to(2))
  println(greetStrings.apply(i))
