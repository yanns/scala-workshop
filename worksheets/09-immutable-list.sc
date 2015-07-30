val oneTwo = List(1, 2)
val oneTwoSeq = Seq(1, 2)

val threeFour = List(3, 4)

oneTwo ++ threeFour
// ==
oneTwo ::: threeFour

val oneTwoThree = 1 :: 2 :: 3 :: Nil
// ==
((Nil.::(3)).::(2)).::(1)

val oneTwoThreeSeq = 1 +: 2 +: 3 +: Nil
oneTwoThreeSeq(2)

// recommended
val v = Vector(1, 2, 3)
v(1)

oneTwoThree.foreach(println)
oneTwoThree
  .map({(i: Int) => i + 10})
  .filter(i => i % 2 != 0)
  .map(i => s"number $i")
  .map(_.toUpperCase)


for (e <- oneTwoThree) {
  println(e)
}

// for comprehension
for (e <- oneTwoThree) yield e + 1

// pattern matching
def checkList[A](l: Seq[A]) =
  l match {
    case Nil =>
      println("list is empty")
    case head +: Nil =>
      println(s"found head '$head'")
    case head +: tail =>
      println(s"found head '$head' with ${tail.length} tail elements")
  }

checkList(Seq(1))
checkList(Seq("abc"))
