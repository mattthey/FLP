package examples

object Func17PAF {
  // not partial
  //oddbad: Int => Int
  val oddbad: Int => Int = {
    case x if x % 2 == 1 =>
      x * 3
  }

  val odd: PartialFunction[Int, Int] = {
    case x if x % 2 == 1 =>
      x * 3
  }

  val even: PartialFunction[Int, Int] = {
    case x if x % 2 == 0 =>
      x / 2
  }

  //partial by hand
  val evenByHand = new PartialFunction[Int, Int] {
    override def isDefinedAt(x: Int): Boolean = x % 2 == 0

    override def apply(arg: Int): Int = arg match {
      case x if x % 2 == 0 => x / 2
    }
  }

  def main(args: Array[String]): Unit = {
    println(evenByHand.lift(3))
    println(evenByHand.lift(4))
  }


//  oddbad(2)
//  scala.MatchError: 2 (of class java.lang.Integer)
//
//  println(odd(2))
//  println(even(3))
//
//   wtf???
//  println(evenByHand(3))
  //scala.MatchError: 3 (of class java.lang.Integer)

//  val lifted: Int => Option[Int] = even.lift
//  even.orElse(odd)
}
