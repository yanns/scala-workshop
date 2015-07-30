val i = 3

i match {
  case 1 => println("January")
  case 2 => println("February")
  case notRecognized => println(s"what to do with $notRecognized?")
}

val month = i match {
  case 1 => "January"
  case 2 => "February"
  case _ => "unknown"
}

// bad style
var month2: String = null
i match {
  case 1 => month2 = "January"
  case 2 => month2 = "February"
  case notRecognized => month2 = s"what to do with $notRecognized?"
}
month2

val x: Any = 3
x match {
  case s: Short => s"thanks for the Short $s"
  case i: Int if i % 2 == 0 =>
    s"thanks for the odd Int $i"
  case i: Int if i % 2 != 0 =>
    s"thanks for the not odd Int $i"
  case s: String => s"thanks for the String $s"
}

val cmd = "go"
cmd match {
  case "start" | "go" => println("starting")
  case "stop" => println("stopping")
}