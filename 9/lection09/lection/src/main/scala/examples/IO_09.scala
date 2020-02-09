package examples

import scala.io.StdIn

object IO_09 extends App {

  sealed trait IO[A] {
    self =>

    def run: A

    def map[B](f: A => B): IO[B] =
      new IO[B] {
        def run = f(self.run)
      }

    def flatMap[B](f: A => IO[B]): IO[B] =
      new IO[B] {
        def run = f(self.run).run
      }

  }

  trait Monad[F[_]] {

    def unit[A](a: => A): F[A]

    def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]

    def map[A, B](ma: F[A])(f: A => B): F[B] =
      flatMap(ma)(a => unit(f(a)))

    def forever[A, B](a: F[A]): F[B] = {
      lazy val t: F[B] = forever(a)
      flatMap(a)(_ => t)
    }
  }

  object IO extends Monad[IO] {

    def unit[A](a: => A): IO[A] = new IO[A] {
      def run = a
    }

    def flatMap[A, B](fa: IO[A])(f: A => IO[B]) = fa flatMap f

    def apply[A](a: => A): IO[A] = unit(a)

  }

  def ReadLine: IO[String] = IO {
    StdIn.readLine
  }

  def PrintLine(msg: String): IO[Unit] = IO {
    println(msg)
  }


  val p = IO.forever(PrintLine("Still going..."))

  p.run
  //  Exception in thread "main" java.lang.StackOverflowError
}
