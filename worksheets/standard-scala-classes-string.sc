val s1 = "Hello"
val s2: String = "Hello"

val s3 = "H" + "ello"

s1 == s2
s1 == s3

s1.equals(s2)
s1 == s2

// instances
s1.eq(s3)

val foo =
  """This is
    a "multiline"
    String """

val foo2 =
  """{
    |  "myField": "abc"
    |}
  """.stripMargin

val foo3 =
  """ cmd1 \
    # | cmd2
  """.stripMargin('#')

val name = "Yann"
val age = 98
s"$name is $age years old"
s"$name will be ${age + 1} years old next year"

val score = 10.0 / 3
s"$name has a score of $score"
f"$name has a score of $score%.2f"

raw"foo\nbar"

