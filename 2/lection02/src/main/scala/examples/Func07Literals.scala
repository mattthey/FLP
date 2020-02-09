package examples

object Func07Literals {

  42
  //Int
  (x: Int) => x + 1

  //Int => Int
  val increment = (x: Int) => x + 1


  increment(0)
  //res2: Int = 1


  val someNumbers = List(-11, -10, -5, 0, 5, 10)
  val predicate = (x: Int) => x > 0

  someNumbers.filter(predicate)
  someNumbers.filter((x: Int) => x > 0)

}
