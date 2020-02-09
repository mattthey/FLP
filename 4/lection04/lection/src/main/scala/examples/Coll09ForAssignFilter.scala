package examples

object Coll09ForAssignFilter {

  val ints = List(1, 2, 3, 4)
  val strings = List("one", "two", "three", "four")

  val product = for {
    i <- ints
    square = i * i
    s <- strings
  } yield (s, square)

  println(product)



  val products = for {
    i <- ints if i % 2 == 0
    s <- strings
  } yield s * i

  println(products)

}
