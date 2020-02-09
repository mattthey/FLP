package examples

import scala.io.StdIn

object IO_08Intro extends App {

  sealed trait IO[A] {
    self =>

    import examples.IO_08Intro.IO._

    def run: A

    def map[B](f: A => B): IO[B] =
      new IO[B] {
        def run = f(self.run)
      }

    def flatMap[B](f: A => IO[B]): IO[B] =
      new IO[B] {
        def run = f(self.run).run
      }

    def **[A](that: IO[A]) = map2(self, that)((_, _))

  }

  trait Monad[F[_]] {
    def unit[A](a: => A): F[A]

    def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]

    def map[A, B](ma: F[A])(f: A => B): F[B] =
      flatMap(ma)(a => unit(f(a)))

    def map2[A, B, C](a: F[A], b: F[B])(f: (A, B) => C): F[C] =
      flatMap(a)(a => map(b)(b => f(a, b)))

    def replicateM[A](n: Int)(f: F[A]): F[List[A]] =
      Stream.fill(n)(f).foldRight(unit(List[A]()))(map2(_, _)(_ :: _))
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

  val readInt: IO[Int] = ReadLine.map(_.toInt)

  val readInts: IO[(Int, Int)] = readInt ** readInt

  val rslt = readInts.run

  PrintLine(rslt.toString()).run

  val lines: IO[List[String]] = IO.replicateM(10)(ReadLine)

  lines.run.foreach(PrintLine(_).run)
}
