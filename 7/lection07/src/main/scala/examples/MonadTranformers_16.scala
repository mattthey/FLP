package examples

object MonadTranformers_16 extends App {

  import MonadTranformers_14_1._

  case class OptionT[M[_], A](value: M[Option[A]])(implicit M: Monad[M]) {

    def unit[A](a: => A): OptionT[M, A] = OptionT(M.unit(Some(a)))

    def flatMap[B](f: A => OptionT[M, B]): OptionT[M, B] =
      OptionT(M.flatMap(value) {
        case None => M.unit(None)
        case Some(a) => f(a).value
      })
  }

  implicit val listMonad = new Monad[List] {
    override def unit[A](a: => A): List[A] = List(a)

    override def flatMap[A, B](ma: List[A])(f: A => List[B]): List[B] = ma.flatMap(f)
  }

  val opT = OptionT(List(Some(1), None, Some(2)))

  //  Right identity
  println(opT.flatMap(a => opT.unit(a)).value == List(Some(1), None, Some(2)))


}
