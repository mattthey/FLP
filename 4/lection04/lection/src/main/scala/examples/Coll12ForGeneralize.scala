package examples

object Coll12ForGeneralize {

  abstract class C[A] {
    def map[B](f: A => B): C[B]
    def flatMap[B](f: A => C[B]): C[B]
    def filter(p: A => Boolean): C[A]
    def foreach(b: A => Unit): Unit
  }

}
