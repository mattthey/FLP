package examples

import examples.Functor_02.Functor
import examples.Monad_07.Monad

object MonadTranformers_14 {

  trait Monad[F[_]] extends Functor[F] {

    self =>

    def unit[A](a: => A): F[A]

    def flatten[A](mma: F[F[A]]): F[A]

    def map[A, B](ma: F[A])(f: A => B): F[B]

    def compose[G[_]](implicit G: Monad[G]): Monad[({type lambda[X] = F[G[X]]})#lambda] =
      new Monad[({type lambda[X] = F[G[X]]})#lambda] {

        def unit[A](a: => A): F[G[A]] = self.unit(G.unit(a))

        def map[A, B](fga: F[G[A]])(f: A => B): F[G[B]] =
          self.map(fga)(ga => G.map(ga)(f))

        override def flatten[A](mma: F[G[F[G[A]]]]): F[G[A]] = ???

      }
  }

}
