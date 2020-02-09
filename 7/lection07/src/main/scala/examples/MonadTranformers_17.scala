package examples

object MonadTranformers_17 extends App {

  import MonadTranformers_14_1._

  case class OptionT[M[_], A](value: M[Option[A]])(implicit M: Monad[M]) {

    def unit[A](a: => A): OptionT[M, A] = OptionT(M.unit(Some(a)))

    def flatMap[B](f: A => OptionT[M, B]): OptionT[M, B] =
      OptionT(M.flatMap(value) {
        case None => M.unit(None)
        case Some(a) => f(a).value
      })
  }

  case class EitherT[M[_], E, A](value: M[Either[E, A]])(implicit M: Monad[M]) {

    def unit[A](a: => A): EitherT[M, E, A] = EitherT(M.unit(Right(a)))

    def flatMap[B](f: A => EitherT[M, E, B]): EitherT[M, E, B] =
      EitherT(M.flatMap(value) {
        case Left(e) => M.unit(Left(e))
        case Right(a) => f(a).value
      })
  }


}
