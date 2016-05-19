val myList = List(1, 2, 3, 4, 5)

for (e <- myList)
  println(e)

val theNewList = for (e <- myList) yield e + 1

for {
  e <- myList
} yield {
  e + 1
}

myList.map(e => e + 1)
