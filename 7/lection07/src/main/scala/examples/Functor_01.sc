package examples

import org.scalacheck.Gen


object Functor_01 {

  val option = Some(4)
  val list = List(1, 2, 3)
  val either = Right(42)
  val gen = Gen.choose(10, 20)

  option.map(_ * 4)
  list.map(_.toString)
  either.map(_ / 21)
  gen.map(i => s"Random $i")

}
