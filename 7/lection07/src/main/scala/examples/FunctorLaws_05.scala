package examples

object FunctorLaws_05 extends App {

  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]

    def unzip[A, B](fab: F[(A, B)]): (F[A], F[B]) =
      (map(fab)(_._1), map(fab)(_._2))
  }

  trait FunctorLaws[F[_]] {

    def identity[F[_], A](fa: F[A])(implicit F: Functor[F]) =
      F.map(fa)(a => a) == fa

    def composition[F[_], A, B, C](fa: F[A])(f: A => B, g: B => C)(implicit F: Functor[F]) =
      F.map(fa)(f andThen g) == F.map(F.map(fa)(f))(g)
  }


  implicit val listFunctor = new Functor[List] {
    def map[A, B](as: List[A])(f: A => B): List[B] =
    //      as map f
      Nil
  }

  val listTupled = List((1, 2), (3, 4), (5, 6))

  println(listFunctor.unzip(listTupled))

}
