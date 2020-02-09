val numbers = List(1, 2, 3)
val returned =
  for (n <- numbers) println(n)


val squares =
  for (n <- numbers) yield n * n

val otherNumbers = List(4, 5, 6)

val complexStuff = for {
  low <- numbers
  high <- otherNumbers
} yield low * high