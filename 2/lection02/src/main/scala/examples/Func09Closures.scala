package examples

object Func09Closures {
  
  val increment = 1

  val increase = (x: Int) => x + increment

  List(1, 2, 3, 4, 5).map(increase)
  println(increment)
}
