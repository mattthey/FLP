package examples

import examples.Functor_02.Functor

object Monad_07 {

  trait Monad[F[_]] extends Functor[F] {
    def unit[A](a: => A): F[A]

    def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]

    def map[A, B](ma: F[A])(f: A => B): F[B] =
      flatMap(ma)(a => unit(f(a)))
  }

}
