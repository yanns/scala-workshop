val i = 4


i match {
  case 1 => println("January")
  case 2 => println("February")
  case other => println(s"do not know $other")
//  case _ => println(s"do not know $i")
}



val month: String = i match {
  case 1 => "January"
  case 2 => "February"
//  case other => s"do not know $other"
  case _ => "Invalid month"
}

val x: Any = "hello"
x match {
  case i: Int => s"thanks for the Int $i"
  case f: Float => s"thanks for the Float $f"
  case s: String => s"thanks for the String $s"
  case _ => "unknown"
}

val cmd = "exit"
cmd match {
  case "start" => println("starting")
  case "stop" | "exit" => println("stopping")
}

val t = 5
t match {
  case n: Int if n % 2 == 0 => println("odd")
  case n: Int => println("even")
}
