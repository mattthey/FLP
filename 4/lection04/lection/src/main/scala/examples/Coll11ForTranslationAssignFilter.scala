package examples

object Coll11ForTranslationAssignFilter {

  val ints = List(1, 2, 3, 4)
  val strings = List("one", "two", "three", "four")


  //assignment
  val products = for {
    i <- ints
    square = i * i
    s <- strings
  } yield (s, square)
  // ~
  val desugaredProducts =
    ints
      .map(x => (x * x, x))
      .flatMap { case (square, i) =>
        strings.map(s => (s, square))
      }


  val flr = for {
    i <- ints
    if i % 2 == 0
  } yield i
  // ~
  val flrDesugared =
    ints
      .withFilter(i => i % 2 == 0)
      .map(i => i)

}
