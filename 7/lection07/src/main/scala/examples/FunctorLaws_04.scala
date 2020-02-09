package examples

import examples.FunctorLaws_03.Functor
import FunctorLaws_03._

object FunctorLaws_04 extends App {

  implicit val listFunctor = new Functor[List] {
    def map[A, B](as: List[A])(f: A => B): List[B] = as map f
  }

  val f = (i: Int) => i * 2
  val g = (i: Int) => i % 2

  val lawsChecker = new FunctorLaws[List] {}

  //  Identity
  println(lawsChecker.identity(List(1, 2, 3)))

  //  Composition
  println(lawsChecker.composition(List(1, 2, 3))(f, g))

}
