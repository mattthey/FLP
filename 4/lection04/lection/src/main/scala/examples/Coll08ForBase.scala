package examples

object Coll08ForBase {

  val ints = List(1, 2, 3, 4)
  val strings = List("one", "two", "three", "four")

  for (i <- ints) println(i)

  println

  for {
    i <- ints
    s <- strings
  } println(s * i)



  val products = for {
    i <- ints
    s <- strings
  } yield s * i



}
