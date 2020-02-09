package examples

object Func16Compose {

  //Function1

  // f
  val add1 = (i: Int) => i + 1
  // g
  val double = (i: Int) => i * 2

  // f andThen g == g(f(_))
  val addThenDouble = add1 andThen double
  addThenDouble(1) //res0: Int = 4

  // f compose g == f(g(_))
  val doubleThenAdd = add1 compose double
  doubleThenAdd(1) //     res1: Int = 3

}
