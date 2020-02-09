package examples

import examples.IO_09.Monad


object IO_10 {

  sealed trait IO[A] {
    def flatMap[B](f: A => IO[B]): IO[B] =
      FlatMap(this, f)

    def map[B](f: A => B): IO[B] =
      flatMap(f andThen (Return(_)))

  }

  case class Return[A](a: A) extends IO[A]

  case class Suspend[A](resume: () => A) extends IO[A]

  case class FlatMap[A, B](sub: IO[A], k: A => IO[B]) extends IO[B]

  object IO extends Monad[IO] {
    def unit[A](a: => A): IO[A] = Return(a)

    def flatMap[A, B](a: IO[A])(f: A => IO[B]): IO[B] = a flatMap f

    @annotation.tailrec
    def run[A](io: IO[A]): A = io match {
      case Return(a) => a
      case Suspend(r) => r()
      case FlatMap(x, f) => x match {
        case Return(a) => run(f(a))
        case Suspend(r) => run(f(r()))
        case FlatMap(y, g) => run(y flatMap (a => g(a) flatMap f))
      }
    }

  }

  def printLine(s: String): IO[Unit] =
    Suspend(() => Return(println(s)))

  val p = IO.forever(printLine("Still going..."))

  IO.run(p)
}
