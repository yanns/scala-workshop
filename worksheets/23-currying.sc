val add: (Int, Int) => Int =
  (x: Int, y: Int) => x + y

add(4, 5)

val add2: (Int) => (Int) => Int =
  (x: Int) => {
    val result = (y: Int) => x + y
    result
  }


add2(4)(5)

// partially applied
val plus3 = add2(3)

plus3(4)


def add3(x: Int)(y: Int): Int = x + y

add3(5)(7)

// partially applied
val plus4 = add3(4) _

plus4(5)



