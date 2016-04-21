val oneTwo = List(1, 2)
oneTwo.+:(0)
oneTwo.+:(0)
oneTwo.+:(0)

// same
oneTwo.+:(0)
0 +: oneTwo

// same
0 :: 1 :: 2 :: 3 :: Nil
Nil.::(3).::(2).::(1).::(0)


oneTwo
val l2 = 0 :: 1 :: 2 :: 3 :: Nil
val l3 = oneTwo ++ l2

l3.foreach(i => println(i))

l3
  .map(i => i + 1)
  .filter(i => i % 2 == 0)
  .map(i => "my number is: " + i)
  .map(s => s.toUpperCase)
  .foreach(println)

val v = Vector(1, 2, 3)

v
  .map(i => i + 1)
  .filter(i => i % 2 == 0)
  .map(i => "my number is: " + i)
  .map(s => s.toUpperCase)
  .foreach(println)

val s: Seq[Int] = Vector(1, 2, 3)

