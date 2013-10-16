import java.util
import scala.util.parsing.json.JSONFormat

/**
 * Created with IntelliJ IDEA.
 * User: erche
 * Date: 16.10.13
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
trait ForEachAble[A]{
  def iterator: Iterator[A]
  def foreach(f: A => Unit) = {
    val iter = iterator
    while (iter.hasNext)
      f(iter.next())
  }
}

trait ForEachAble2[A] extends ForEachAble[A] {
  override def foreach(f: (A) => Unit): Unit = super.foreach(f)
}

trait JsonAble {
  def toJson(): String = JSONFormat.defaultFormatter(this)
}

val list = new util.ArrayList[Int]() with ForEachAble[Int]
list.add(1)
list.add(2)

list.foreach(x => println(x))