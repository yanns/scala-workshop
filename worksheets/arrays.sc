val greetStrings = new Array[String](3)

greetStrings(0) = "hello"
greetStrings(1) = ", "
greetStrings(2) = "world!"

for (i <- 0 to 2)
  print(greetStrings(i))

// same
0 to 2
0.to(2)

// same
greetStrings(0)
greetStrings.apply(0)


// same
greetStrings(0) = "hello"
greetStrings.update(0, "hello")


val greetStrings2 = Array("hello", ", ", "world")
Array.apply("hello", ", ", "world")
