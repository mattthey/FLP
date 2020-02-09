package examples

object IO_16Streaming {

  sealed trait Process[I, O] {

    def apply(s: Stream[I]): Stream[O] = this match {
      case Halt() => Stream()
      case Await(recv) => s match {
        case h #:: t => recv(Some(h))(t)
        case xs => recv(None)(xs) // Stream is empty
      }
      case Emit(h, t) => h #:: t(s)
    }

    def repeat: Process[I, O] = {
      def go(p: Process[I, O]): Process[I, O] = p match {
        case Halt() => go(this)
        case Await(recv) => Await {
          case None => recv(None)
          case i => go(recv(i))
        }
        case Emit(h, t) => Emit(h, go(t))
      }

      go(this)
    }

    def |>[O2](p2: Process[O, O2]): Process[I, O2] = {
      p2 match {
        case Halt() => Halt()
        case Emit(h, t) => Emit(h, this |> t)
        case Await(f) => this match {
          case Emit(h, t) => t |> f(Some(h))
          case Halt() => Halt() |> f(None)
          case Await(g) => Await((i: Option[I]) => g(i) |> p2)
        }
      }
    }

  }

  case class Emit[I, O](head: O, tail: Process[I, O] = Halt[I, O]()) extends Process[I, O]

  case class Await[I, O](recv: Option[I] => Process[I, O]) extends Process[I, O]

  case class Halt[I, O]() extends Process[I, O]

  object Process {

    def liftOne[I, O](f: I => O): Process[I, O] =
      Await {
        case Some(i) => Emit(f(i))
        case None => Halt()
      }

    def lift[I, O](f: I => O): Process[I, O] =
      liftOne(f).repeat

    def filter[I](p: I => Boolean): Process[I, I] =
      Await[I, I] {
        case Some(i) if p(i) => Emit(i)
        case _ => Halt()
      }.repeat

    def loop[S, I, O](z: S)(f: (I, S) => (O, S)): Process[I, O] = /*Await((i: I) => f(i,z) match {
        case (o,s2) => Emit(o, loop(s2)(f))
      })*/
      Await[I, O] {
        case Some(i) => f(i, z) match {
          case (o, s2) => Emit(o, loop(s2)(f))
        }
        case None => Halt[I, O]()
      }

    def count[I]: Process[I, Int] =
      loop(0)((_: I, n) => (n + 1, n + 1))

    def exists[I](f: I => Boolean): Process[I,Boolean] =
      lift(f) |> loop(false)((b:Boolean,s) => (s || b, s || b))

  }

  import Process._


  val pipe = filter[Int](_ % 2 == 0) |> liftOne[Int, Int](_ + 1).repeat

  val output = pipe(Stream(1, 2, 3, 4)).toList

}
