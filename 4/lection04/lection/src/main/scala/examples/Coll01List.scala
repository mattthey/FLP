package examples

object Coll01List {

  sealed trait List[+A]

  case object Nil extends List[Nothing]

  case class Cons[+A](head: A, tail: List[A]) extends List[A]

  def main(args: Array[String]): Unit = {
    val l1 = Cons(0, Nil)
    val l2 = Cons(1, l1)

    println(l2.tail eq l1)
  }
}
