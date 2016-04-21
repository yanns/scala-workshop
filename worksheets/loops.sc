val args = "Scala is fun".split(" ")

var i = 0
while (i < args.length) {
  println(args(i))
  println(args.apply(i))
  i += 1
}

args.foreach((arg: String) => { println(arg) })
args.foreach(arg => { println(arg) })
args.foreach(arg => println(arg))
args.foreach(println(_))
args.foreach(println)
args foreach println

args.map(_.toUpperCase)

for (arg <- args) {
  println(arg)
}

for (arg <- args)
  println(arg)
