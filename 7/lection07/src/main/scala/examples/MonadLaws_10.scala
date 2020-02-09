package examples

object MonadLaws_10 {

  abstract class Option[+T] {
    def flatMap[U](f: T => Option[U]): Option[U] = this match {
      case Some(x: T) => f(x)
      case None => None
    }
  }

  final case class Some[+T](value: T) extends Option

  final case object None extends Option

  //  Left identity
  //  Some(x) flatMap f == f(x)

  //  Some(x) flatMap f
  //
  //  ==  Some(x) match {
  //    case Some(x) => f(x)
  //    case None => None
  //  }
  //  == f (x)


}
