package examples

import examples.Functor_02.Functor

object MonadOthCombi_13 {

  trait FlatMapMonad[F[_]] extends Functor[F] {
    def unit[A](a: => A): F[A]

    def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]

    def map[A, B](ma: F[A])(f: A => B): F[B] =
      flatMap(ma)(a => unit(f(a)))


    def flatten[A](mma: F[F[A]]): F[A] = flatMap(mma)(identity)

    def compose[A, B, C](f: A => F[B], g: B => F[C]): A => F[C] =
      a => flatMap(f(a))(g)

  }

  trait FlattenMonad[F[_]] extends Functor[F] {
    def unit[A](a: => A): F[A]

    def map[A, B](ma: F[A])(f: A => B): F[B]

    def flatten[A](mma: F[F[A]]): F[A]


    def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B] = flatten(map(ma)(f))
  }

  trait ComposedenMonad[F[_]] extends Functor[F] {
    def unit[A](a: => A): F[A]

    def compose[A, B, C](f: A => F[B], g: B => F[C]): A => F[C]


    def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B] =
      compose((_: Unit) => ma, f)(())
  }

}
