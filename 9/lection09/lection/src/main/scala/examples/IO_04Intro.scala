package examples

object IO_04Intro {

  case class Player(name: String, score: Int)

  case class Contest(contestantsList: Map[Byte, Player])

  trait IO {
    def run: Unit
  }

  def PrintLine(msg: String): IO =
    new IO {
      def run = println(msg)
    }

  def enterContestant: Player = {
    val prompt: IO = PrintLine("Enter a contestant and his score")

    ???
  }

}
