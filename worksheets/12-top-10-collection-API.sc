import section10.Section10._

words.head
words.tail.take(5)

words.isEmpty
words.nonEmpty

words.foreach(println(_))

words.filter({s => s.length != 0}).take(5)
words.drop(4).take(5)

words.find(_ == "Scala")
words.exists(_ == "type-safe")
words.exists(_ == "dynamic")
words.contains("Scala")

words.reverse.take(5)

words.takeWhile(_ != "Java")
words.zipWithIndex.take(5)

val lengths = words.map(_.length)
val listAndLengths = words.zip(lengths)
listAndLengths.unzip

lengths.sum
lengths.min
lengths.max

words.minBy(_.length)
words.maxBy(_.length)

words.filter(_.nonEmpty).forall(_.length > 0)
words.filter(_.nonEmpty).forall(_.length > 1)

lengths.sorted
words.sortBy(_.length)
words.partition(_.length > 5)

words.distinct
words.toSet

words.groupBy(_.length)
