package section10

object Section10 {
  val text =
    """Scala (/ˈskɑːlə/ skah-lə) is an object-functional programming and scripting language for general software applications,
      |statically typed, designed to concisely express solutions in an elegant,[6] type-safe and lightweight (low ceremonial) manner.
      |Scala has full support for functional programming (including currying, pattern matching, algebraic data types, lazy evaluation,
      |tail recursion, immutability, etc.). It cleans up what are often considered poor design decisions in Java (such as type erasure,
      |checked exceptions, the non-unified type system) and adds a number of other features designed to allow cleaner, more concise
      |and more expressive code to be written.[5]
      |
      |It is intended to be compiled to Java bytecode, so the resulting executable runs on the JVM, and Java libraries can be used
      |directly in Scala code and vice-versa. Like Java, Scala is statically typed and object-oriented, and uses a curly-brace syntax
      |reminiscent of C. Unlike Java, Scala has many features of functional programming languages like Scheme, Standard ML and Haskell,
      |including anonymous functions, type inference, list comprehensions (known in Scala as "for-comprehensions"), lazy initialization,
      |extensive language and library support for avoiding side-effects, for pattern matching, case classes, delimited continuations,
      |higher-order types, and much better support for covariance and contravariance. Scala has a unified type system (as in C#, but
      |unlike in Java), where all types, including primitive types like integer and boolean, are subclasses of the type Any. Scala
      |likewise has other features present in C# but not Java, including anonymous types, operator overloading, optional parameters,
      |named parameters, raw strings (that may be multi-line in Scala), and no checked exceptions.
      |
      |The name Scala is a portmanteau of "scalable" and "language", signifying that it is designed to grow with the demands of its users.
      |James Strachan, the creator of Groovy, described Scala as a possible successor to Java.
    """.stripMargin


  val words = text.replace(","," ").replace(".", " ").replace("\n", " ").replace("(", "").replace(")","").replaceAll("\\s", " ").split(" ").toVector

  def wordsWithoutEmptyString: Seq[String] = ???

  def dictionary: Map[Char, Seq[String]] = ???

}
