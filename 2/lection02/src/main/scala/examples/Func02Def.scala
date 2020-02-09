package examples

object Func02Def {

  def aMethod(x: Int): Int = x + 1

  def anEffect(x: Int): Unit =
    println("x + 1 = " + x + 1)

  def aMethodTwo(x: Int, s: String) =
    s + x + 1

}
