package examples

object Coll02List4 {

  sealed trait List[+A]

  case object Nil extends List[Nothing]

  case class Cons[+A](head: A, tail: List[A]) extends List[A]


  object List {

    def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = {
      l match {
        case Nil => z
        case Cons(h, t) => foldLeft(t, f(z, h))(f)
      }
    }

    def reverse[A](l: List[A]): List[A] =
      foldLeft(l, Nil: List[A])((xs, x) => Cons(x, xs))

    def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B =
      foldLeft(reverse(as), z)((b, a) => f(a, b))

  }

}