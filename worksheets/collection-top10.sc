import section10.Section10.words

words.head
words.headOption
words.tail
words.tail.take(5)
words.isEmpty
words.nonEmpty
//words.foreach(println(_))

words.filter(_.isEmpty)
words.filter(_.nonEmpty).take(5)
words.drop(4).take(5)
words.slice(4, 9)

words.find(_ == "Scala")
words.find(_ == "Spring")
words.find(e => e.toUpperCase == "SCALA")

words.exists(_ == "type-safe")
words.exists(e => e.toUpperCase == "TYPE-SAFE")
words.exists(e => e.toUpperCase == "DYNAMIC")
words.contains("type-safe")


words.reverse.take(5)
words.takeWhile(_ != "Java")


words.zipWithIndex.find(_._1 == "Groovy")


val lengths = words.map(_.length)
val listAndLengths = words.map(e => e -> e.length)
words.zip(lengths)

words.zip(Seq(5, 6, 7))


words.zip(lengths).unzip

lengths.sum
lengths.min
lengths.max
words.maxBy(_.length)
words.filter(_.nonEmpty).forall(_.length > 0)
words.filter(_.nonEmpty).forall(_.length > 1)

lengths.sorted
words.sortBy(_.length).reverse

words.partition(_.length > 5)
words.distinct
words.toSet
words.groupBy(_.length)


