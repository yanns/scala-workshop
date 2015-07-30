import scala.collection.mutable

val l = new mutable.ListBuffer[String]()
l += "a"
l += "b"

l.apply(1)
l(1)

l.toList

