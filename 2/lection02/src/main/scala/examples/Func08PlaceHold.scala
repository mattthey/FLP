package examples

object Func08PlaceHold {

  //x => x + 1
  val fFull: (Int => Int) = { (x: Int) => x + 1 }
  val f = (_: Int) + 1
  //res0: Int = 1

  //val g = _ + _
  val g: (Int, Int) => Int = _ * _
  g(1, 3) == 4
  g(3, 4) == 12

  //val g1: (Int, Int) => Int = _ * _ * _

  //val g: _root_.scala.Function2[Int, Int, Int] = ((x$3, x$4) => x$3.$times(x$4));

  val h: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
    override def apply(x: Int, y: Int): Int = x * y
  }

  h(4, 4) == h.apply(4, 4)


}
