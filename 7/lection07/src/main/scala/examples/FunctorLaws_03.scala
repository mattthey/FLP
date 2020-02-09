package examples

object FunctorLaws_03 extends App {

  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }

  trait FunctorLaws[F[_]] {

    def identity[F[_], A](fa: F[A])(implicit F: Functor[F]) =
      F.map(fa)(a => a) == fa

    def composition[F[_], A, B, C](fa: F[A])(f: A => B, g: B => C)(implicit F: Functor[F]) =
      F.map(fa)(f andThen g) == F.map(F.map(fa)(f))(g)
  }

}
