import java.util.{List => JList, ArrayList}
//import java.util.{_, List => _}

val javaList: JList[String] = new ArrayList[String]()
javaList.add("hello")

def f = {
  import scala.collection.JavaConversions._
  val scalaList: List[String] = javaList.toList
  scalaList
}

val javaString: String = null
Option(javaString)