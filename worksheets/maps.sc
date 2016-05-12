import scala.collection.mutable

val treasureMap = new mutable.HashMap[Int, String]
treasureMap += ((1, "go to island"))

// tuple
val t = (2, "Find big X on ground")
t._1
t._2
treasureMap += t

val t2 = 3 -> "Dig."

treasureMap += 3 -> "Dig."


treasureMap(2)
// exception
//treasureMap(4)

treasureMap.get(2)
treasureMap.get(4)

val immutableMap = Map(
  1 -> "go to island",
  2 -> "Find big X on ground",
  3 -> "Dig.")

for ((key, value) <- immutableMap) {
  println(key)
  println(value)
}

immutableMap.map(e => e)
// not compiling - but we want that!!!
// immutableMap.map((k, v) => k)
immutableMap.map { case (k, v) => k }

