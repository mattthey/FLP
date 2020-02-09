package examples

import examples.Monad_07.Monad

object MonadUsage_19_State extends App {

  case class State[S, +A](run: S => (A, S)) {
    def unit[A](a: => A): State[S, A] = State(s => (a, s))

    def map[B](f: A => B): State[S, B] =
      flatMap(a => unit(f(a)))

    def flatMap[B](f: A => State[S, B]): State[S, B] = State(s => {
      val (a, s1) = run(s)
      f(a).run(s1)
    })
  }

  def stateMonad[S] = new Monad[({type lambda[x] = State[S, x]})#lambda] {
    def unit[A](a: => A): State[S, A] = State(s => (a, s))

    override def flatMap[A, B](st: State[S, A])(f: A => State[S, B]): State[S, B] =
      st flatMap f
  }


  def nextInt(seed: Long) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val n = (newSeed >>> 16).toInt
    (n, newSeed)
  }

  val rndMonad = stateMonad[Long].unit(1L)

  rndMonad.map(_ / (Int.MaxValue.toDouble + 1)).run(1L)
  //  (4.6566128730773926E-10,1)

  val rng = rndMonad.flatMap(_ => State(nextInt))

  println(rng.run(89))
  //(34242652,2244126448624)
  println(rng.run(2244126448624L))
  //(245320081,16077296862523)
}
