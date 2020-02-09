package examples

import org.scalacheck.Gen


object Monad_05 {

  val option = Some(4)
  val list = List(1, 2, 3)
  val either = Right(42)
  val gen = Gen.choose(10, 20)

  option.flatMap(v => Some(v * 4))
  list.flatMap(_ => (1 to 5))
  either.flatMap(v => Right(v / 21))
  gen.flatMap { n =>
    Gen.choose(2 * n, 500).map { m =>
      (n, m)
    }
  }

}
