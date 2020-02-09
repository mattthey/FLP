package examples

object Coll10ForTranslationBase {


  //without yield
  val ints = List(1, 2, 3, 4)
  val strings = List("one", "two", "three", "four")

  for (i <- ints) println(i)
  // ~
  ints.foreach { i =>
    println(i)
  }

  println

  for {
    i <- ints
    s <- strings
  } println(s * i)
  // ~
  ints.foreach { i =>
    strings.foreach { s =>
      println(s * i)
    }
  }


  //with yield
  val products = for {
    i <- ints
    s <- strings
  } yield s * i
  // ~
  val productsDesugared =
    ints.flatMap { i =>
      strings.map(s => s * i)
    }


}
