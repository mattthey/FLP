package examples

import examples.IO_02Intro._

object IO_03Intro {

  trait IO {
    def run: Unit
  }

  def PrintLine(msg: String): IO =
    new IO {
      def run = println(msg)
    }

  def contest(p1: Player, p2: Player): IO =
    PrintLine(winnerMsg(winner(p1, p2)))
}
