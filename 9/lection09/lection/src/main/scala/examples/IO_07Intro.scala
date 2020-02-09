package examples

import scala.io.StdIn

object IO_07Intro {

  import IO_06Intro._

  def ReadLine: IO[String] = IO {
    StdIn.readLine
  }

  def PrintLine(msg: String): IO[Unit] = IO {
    println(msg)
  }

  case class Player(name: String, score: Int)

  case class Contest(contestantsList: Map[Byte, Player])


  def enterContestant: IO[Player] = for {
    _ <- PrintLine("Enter a contestant name")
    name <- ReadLine
    _ <- PrintLine("Enter contestant's score")
    score <- ReadLine.map(_.toInt)
  } yield (Player(name, score))

}
