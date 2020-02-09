package examples

object MonadLaws_11 {

  abstract class Option[+T] {
    def flatMap[U](f: T => Option[U]): Option[U] = this match {
      case Some(x: T) => f(x)
      case None => None
    }
  }

  final case class Some[+T](value: T) extends Option

  final case object None extends Option

  //  Right identity
  //  opt flatMap Some == opt

  //  opt flatMap Some
  //
  //  == opt match {
  //    case Some(x) => Some(x)
  //    case None => None
  //  }
  //  == opt


}
