package examples

object MonadTranformers_15 extends App {

  import MonadTranformers_14_1._

  val listMonad = new Monad[List] {
    override def unit[A](a: => A): List[A] = List(a)

    override def flatMap[A, B](ma: List[A])(f: A => List[B]): List[B] = ma.flatMap(f)
  }

  val optionMonad = new Monad[Option] {
    override def unit[A](a: => A): Option[A] = Some(a)

    override def flatMap[A, B](ma: Option[A])(f: A => Option[B]): Option[B] = ma.flatMap(f)
  }

  val listOptions = listMonad compose optionMonad

  //  Right identity
  println(listOptions.flatMap(List(Some(1), None, Some(2)))(a => listOptions.unit(a)) == List(Some(1), None, Some(2)))

}
