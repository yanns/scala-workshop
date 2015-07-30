import scala.beans.BeanProperty

//companion object
object model {

  class Greeter(val greeting: String) {
    def greet() = println(Greeter.worldify(greeting))
  }
  object Greeter {
    def worldify(s: String) = s"$s, world!"
    def apply(greeting: String): Greeter = new Greeter(greeting)
  }

}

model.Greeter.worldify("hi")
val g = new model.Greeter("test")
g.greeting
g.greet()

val g2 = model.Greeter("test")
g2.greet()


case class CaseGreeter(greeting: String, count: Int = 1) {
  def greet() = println(greeting)
}
val cg = CaseGreeter("hi")
cg.greeting
cg.greet()

def isOriginal(cg: CaseGreeter) = cg match {
  case CaseGreeter("hello", _) => println("not very original")
  case CaseGreeter(_, 1) => println("only one time")
  case c => println(s"impressive: $c")
}
isOriginal(cg)
isOriginal(CaseGreeter("hello"))
isOriginal(CaseGreeter("hi", 6))


case class JavaGreeter(@BeanProperty greeting: String)
val jg = JavaGreeter("hi")
jg.greeting
jg.getGreeting()