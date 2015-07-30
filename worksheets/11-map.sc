import scala.collection.mutable

val treasureMap = new mutable.HashMap[Int, String]()

val step1: (Int, String) = 1 -> "Go to island"
val step2: (Int, String) = (2, "Find big X on ground")
treasureMap += step1
treasureMap += step2
treasureMap += 3 -> "Dig."

treasureMap.apply(1)
treasureMap(3)
for (kv <- treasureMap) {
  println(kv)
}


val romanNumeral = Map(
  1 -> "I",
  2 -> "II"
)
//romanNumeral += 3 -> "III"
romanNumeral(2)
romanNumeral + (3 -> "III")
romanNumeral
