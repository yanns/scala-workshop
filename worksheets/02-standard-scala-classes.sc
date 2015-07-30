val s1 = "hello"
val s2 = "hello"
val s3 = "h" + "ello"

s1 == s2
s1 == s3

val foo =
  """this is
     a multiline
     String"""

val speech =
  """this is
    |a multiline
    |String""".stripMargin

val speech2 =
  """this is
    #a multiline
    #String""".stripMargin('#')

val name = "Yann"
val age = 38
s"$name is $age year old"
s"$name is ${age + 1} year old next year"

val score = 10.0 /3
s"$name has a score of $score"
f"$name has a score of $score%.0f"

"foo\nbar"
"foo\\nbar"
raw"foo\nbar"


