package examples

import examples.Functor_02.Functor

object MonadTranformers_14_1 {

  trait Monad[F[_]] extends Functor[F] {

    self =>

    def unit[A](a: => A): F[A]

    def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]

    def flatten[A](mma: F[F[A]]): F[A] = flatMap(mma)(identity)

    def map[A, B](ma: F[A])(f: A => B): F[B] =
      flatMap(ma)(a => unit(f(a)))

    def compose[G[_]](implicit G: Monad[G]): Monad[({type lambda[X] = F[G[X]]})#lambda] =
      new Monad[({type lambda[X] = F[G[X]]})#lambda] {

        def unit[A](a: => A): F[G[A]] = self.unit(G.unit(a))

        def flatMap[A, B](fga: F[G[A]])(f: A => F[G[B]]): F[G[B]] = {
          val nested = self.map(fga) { ga => G.map(ga) { a => f(a): F[G[B]] }: G[F[G[B]]] }: F[G[F[G[B]]]]
          flatten(nested)
        }

      }
  }

}
