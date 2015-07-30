val set = Set(1, 2)

import scala.collection.mutable

val jetSet = new mutable.HashSet[String]()
jetSet += "Lear"
jetSet += ("Boeing", "Airbus")

jetSet.contains("Lear")
jetSet.contains("Cessnar")
jetSet.toSet

jetSet.map(s => s.toUpperCase)