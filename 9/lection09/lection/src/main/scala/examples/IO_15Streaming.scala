package examples

object IO_15Streaming {

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

    def lift[I,O](f: I => O): Process[I,O] =
      liftOne(f).repeat

    def filter[I](p: I => Boolean): Process[I, I] =
      Await[I, I] {
        case Some(i) if p(i) => Emit(i)
        case _ => Halt()
      }.repeat

  }

  import Process._

  val p = liftOne((x: Int) => x * 2)

  val xs = p(Stream(1, 2, 3)).toList

  val even = filter((x: Int) => x % 2 == 0)

}
