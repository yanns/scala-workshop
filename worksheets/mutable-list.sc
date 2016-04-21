import scala.collection.mutable

val l = new mutable.ListBuffer[String]()
l.+=("a")
l += "b"

val l2 = new mutable.ListBuffer[String]()
l2 += "c"
l2 += "d"

l ++= l2

l.toList
