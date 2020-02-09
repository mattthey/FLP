package examples

import examples.Functor_02.Functor

object MonadLaws_08 {

  trait Monad[F[_]] extends Functor[F] {
    def unit[A](a: => A): F[A]

    def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]

    def map[A, B](ma: F[A])(f: A => B): F[B] =
      flatMap(ma)(a => unit(f(a)))
  }

  trait MonadLaws[F[_]] {

    //  unit(a).flatMap(f) == f(a)
    def leftIdentity[F[_], A, B](a: A)(f: A => F[B])(implicit M: Monad[F]) =
      M.flatMap(M.unit(a))(f) == f(a)

    //  ma.flatMap(unit) == ma
    def rightIdentity[F[_], A, B](ma: F[A])(implicit M: Monad[F]) =
      M.flatMap(ma)(a => M.unit(a)) == ma

    //  ma.flatMap(f).flatMap(g) == ma.flatMap(x => f(x).flatMap(g))
    def associativity[F[_], A, B, C](ma: F[A])(f: A => F[B], g: B => F[C])(implicit M: Monad[F]) =
      M.flatMap(M.flatMap(ma)(f))(g) == M.flatMap(ma)(x => M.flatMap(f(x))(g))

  }

}
