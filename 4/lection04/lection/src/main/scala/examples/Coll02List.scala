package examples

object Coll02List {

  sealed trait List[+A]

  case object Nil extends List[Nothing]

  case class Cons[+A](head: A, tail: List[A]) extends List[A]


  object List {

    def sum(ns: List[Int]): Int = ns match {
      case Nil => 0
      case Cons(x, xs) => x + sum(xs)
    }

    def product(ds: List[Double]): Double = ds match {
      case Nil => 1.0
      case Cons(x, xs) => x * product(xs)
    }
  }

}