package examples

import scala.util.control.NonFatal

object MonadTry_18 {

  abstract class Try[+T] {
    def flatMap[U](f: T => Try[U]): Try[U] = this match {
      case Success(x) => try f(x) catch {
        case NonFatal(ex) => Failure(ex)
      }
      case fail: Failure => fail
    }

    def map[U](f: T => U): Try[U] = this match {
      case Success(x) => Try(f(x))
      case fail: Failure => fail
    }
  }

  case class Success[T](x: T) extends Try[T]

  case class Failure(ex: Throwable) extends Try[Nothing]

  object Try {

    //unit = Try.apply
    def apply[T](expr: => T): Try[T] =
      try Success(expr)
      catch {
        case NonFatal(ex) => Failure(ex)
      }
  }

  //  unit(a).flatMap(f) == f(a)
  //Try(expr) flatMap f != f(expr)

}
