package section1

object Section1 {


  // implement the ???
  // and change if necessary the 'def' with 'val', 'var' or 'lazy val'
  // <start changes>
  val message: String = "Hello, World"
  var greeting: String = "Hello, World"
  def max(x: Int, y: Int): Int =
    if (x > y) x else y

//  def max3(x: Int, y: Int): Int = {
//    if (x > y) x else y
//  }
//  def max4(x: Int, y: Int): Int = {
//    if (x > y) {
//      x
//    } else {
//      y
//    }
//  }
//  def max(x: Int, y: Int): Int = {
//    if (x > y) {
//      return x
//    } else {
//      return y
//    }
//  }

  // <stop changes>

  // check the difference between call-by-name and call-by-value (default)
  def loop: Int = loop
  def constOne(x: Int, y: Int) = 1

  // <start changes>
  def newConstOne(x: => Int, y: => Int) = 1
  // <stop changes>


  def expr(): String = {

    // track the evaluations of x, y and z.
    var trackEvaluations = ""

    // change if necessary the 'def' with 'val', 'var' or 'lazy val'
    // <start changes>
    val x = {
      trackEvaluations += "x"
      1
    }

    lazy val y = {
      trackEvaluations += "y"
      2
    }

    def z = {
      trackEvaluations += "z"
      3
    }
    // <stop changes>

    z + y + x + z + y + x

    trackEvaluations
  }
}
