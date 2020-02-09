package examples

import examples.MonadLaws_08.{Monad, MonadLaws}

object MonadLaws_09 extends App {


  implicit val listMonad = new Monad[List] {
    override def unit[A](a: => A): List[A] = List(a)

    override def flatMap[A, B](ma: List[A])(f: A => List[B]): List[B] = ma.flatMap(f)
  }

  val f = (i: Int) => List(i * 2)
  val g = (i: Int) => List(i % 2)

  val lawsChecker = new MonadLaws[List] {}

  //  Left identity
  println(lawsChecker.leftIdentity(1)(f))

  //  Right identity
  println(lawsChecker.rightIdentity(List(1, 2, 3)))

  //  Associativity
  println(lawsChecker.associativity(List(1, 2, 3))(f, g))

}
