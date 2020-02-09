package examples

object Coll02List5 {

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

    def sum(ns: List[Int]): Int =
      foldRight(ns, 0)(_ + _)

    def product(ds: List[Double]): Double =
      foldRight(ds, 1.0)(_ * _)

    def foreach[A](l: List[A])(f: A => Unit): Unit =
      foldLeft(l, ())((_, a) => f(a))

    def length[A](l: List[A]): Int = foldLeft(l, 0)((b, _) => b + 1)

    def map[A, B](l: List[A])(f: A => B): List[B] =
      foldRight(l, Nil: List[B])((a, lb) => Cons(f(a), lb))

    def filter[A](as: List[A])(f: A => Boolean): List[A] =
      foldRight(as, Nil: List[A])((a, l) => if (f(a)) Cons(a, l) else l)

    def append[A](l: List[A], r: List[A]): List[A] =
      foldRight(l, r)(Cons(_, _))

    def flatten[A](l: List[List[A]]): List[A] =
      foldRight(l, Nil: List[A])(append)

    def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] =
      flatten(map(l)(f))

  }

}