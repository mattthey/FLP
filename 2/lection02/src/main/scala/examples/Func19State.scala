package examples

object Func19State {

  case class State[S, +A](run: S => (A, S)) {

    def map[B](f: A => B): State[S, B] =
      State(s => {
        val (a, sa) = run(s)
        (f(a), sa)
      })

    def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] =
      flatMap(a => sb.map(b => f(a, b)))

    def flatMap[B](f: A => State[S, B]): State[S, B] =
      State(s => {
        val (a, sa) = run(s)
        f(a).run(sa)
      })
  }

  object State {
    def unit[S, A](a: A): State[S, A] =
      State(s => (a, s))
  }

  def main(args: Array[String]): Unit = {
    val s = State()
    println(s)
  }

}
