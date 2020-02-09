package examples

object MonadLaws_12 {

  abstract class Option[+T] {
    def flatMap[U](f: T => Option[U]): Option[U] = this match {
      case Some(x: T) => f(x)
      case None => None
    }
  }

  final case class Some[+T](value: T) extends Option

  final case object None extends Option

  //  Associativity
  //  opt flatMap f flatMap g == opt flatMap (x => f(x) flatMap g)

  //  opt flatMap f flatMap g
  //  == opt match {
  //    case Some(x) => f(x)
  //    case None => None
  //  } match
  //  {
  //    case Some(y) => g(y)
  //    case None => None
  //  }

  //  == opt match {
  //    case Some(x) =>
  //      f(x) match {
  //        case Some(y) => g(y)
  //        case None => None
  //      }
  //    case None =>
  //      None match {
  //        case Some(y) => g(y)
  //        case None => None
  //      }
  //  }

  //  == opt match {
  //    case Some(x) =>
  //      f(x) match {
  //        case Some(y) => g(y)
  //        case None => None
  //      }
  //    case None => None
  //      }

  //  == opt match {
  //    case Some(x) => f(x) flatMap g
  //    case None => None
  //  }

  //  == opt flatMap(x => f(x) flatMap g)

}

