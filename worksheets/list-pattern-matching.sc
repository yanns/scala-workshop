val l = List(1, 2, 3)

l match {
  case h :: tail => println(s"head: $h, tail: $tail")
  case Nil => println("empty list")
}



val s: Seq[Int] = Vector(1)

s match {
  case head +: Nil => println(s"only one element: $head")
  case head +: tail => println(s"head: $head, tail: $tail")
  case Nil => println("empty list")
}


