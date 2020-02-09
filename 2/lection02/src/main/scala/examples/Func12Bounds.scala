package examples

object Func12Bounds {

  trait Grandfather

  class Father extends Grandfather

  class Son extends Father

  def generationY[T <: Father](x: T): T = x

  def generationX[T >: Father](x: T): T = x

//  val x11: Grandfather = generationX(new Grandfather {})
  val x12: Father = generationY(new Father)
  val x13: Son = generationY(new Son)

  val x21: Grandfather = generationX(new Grandfather {})
  val x22: Father = generationX(new Father)
//  val x23: Son = generationY(new Son)
  val x24: Father = generationX(new Son)

}
