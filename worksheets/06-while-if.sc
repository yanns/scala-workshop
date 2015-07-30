val args1 = "Scala is fun".split(" ")
var i = 0
while (i < args1.length) {
  println(args1(i))
  i += 1
}

i = 0
while (i < args1.length) {
  if (i != 0) {
    print(" ")
  }
  print(args1.apply(i))
  i += 1
}

def f(s: String): Unit = println(s)
args1.foreach(f)
args1.foreach({(s: String) => {println(s)}})
args1.foreach({s => println(s)})
args1.foreach(s => println(s))
args1.foreach(println(_))
args1.foreach(println)
args1 foreach println

for (arg <- args1) {
  println(arg)
}
