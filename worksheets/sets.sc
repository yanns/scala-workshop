import scala.collection.mutable

val jetSet = new mutable.HashSet[String]()

jetSet += "Lear"
jetSet += "Boeing"

jetSet.contains("hello")
jetSet.contains("Lear")


val immutableSet = Set("Lear", "Boeing")
