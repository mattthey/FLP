package examples

import examples.Monad_07.Monad

object MonadUsage_19_Id extends App {

  case class Id[A](value: A) {
    def map[B](f: A => B): Id[B] = Id(f(value))

    def flatMap[B](f: A => Id[B]): Id[B] = f(value)
  }

  val monadId = new Monad[Id] {
    def unit[A](a: => A) = Id(a)

    override def flatMap[A, B](ida: Id[A])(f: A => Id[B]): Id[B] = ida flatMap f
  }


  val oneId = monadId.unit[Int](1)
  val twoId = monadId.unit[Int](2)

  monadId.unit(1).map(_ + 1)
  //Id(2)

  monadId.unit(2).flatMap(i => Id(s"#$i"))
  //Id(#2)

  for {
    one <- oneId
    two <- twoId
  } yield (s"Bye $one and $two")
  //Id(Bye 1 and 2)

}
