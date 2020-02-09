package examples

import examples.Monad_07.Monad

object MonadUsage_19_Reader {

  case class Reader[R, A](run: R => A) {
    self =>
    def unit[A](a: => A): Reader[R, A] = Reader(_ => a)

    def flatMap[B](f: A => Reader[R, B]): Reader[R, B] =
      Reader((r: R) => f(self.run(r)).run(r))

    def map[B](f: A => B): Reader[R, B] =
      self.flatMap((a: A) => unit(f(a)))
  }


  def readerMonad[R] = new Monad[({type f[x] = Reader[R, x]})#f] {
    def unit[A](a: => A): Reader[R, A] = Reader(_ => a)

    override def flatMap[A, B](rd: Reader[R, A])(f: A => Reader[R, B]): Reader[R, B] =
      rd flatMap (f)
  }

}
