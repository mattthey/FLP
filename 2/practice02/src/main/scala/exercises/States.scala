package exercises

import examples.States._
import scala.annotation.tailrec

//  Написать крестики-нолики
//  На вход список клетка - игрок
//  По списку проходим последовательно
//  Если клетка, на которую пытаемся сходить - занята,
//  ничего не происходит, ход сгорает, идём дальше
//  Пустой инпут - ничья
//  Вывести рез-т игры для заданных входных данных

//  while, return и var запрещены
//  try catch не использовать
//  5 баллов

object States {

  sealed trait Player

  case object X extends Player

  case object O extends Player

  sealed trait Cell

  case object AA extends Cell

  case object AB extends Cell

  case object AC extends Cell

  case object BA extends Cell

  case object BB extends Cell

  case object BC extends Cell

  case object CA extends Cell

  case object CB extends Cell

  case object CC extends Cell

  case class Game(cells: Map[Cell, Player] = Map())

  type GameState = State[Game, (Cell, Player)]

  val winMsg = (plr: Player) => s"Der Spieler $plr hat gewonnen"

  val standOffMsg = "Unentschieden"

  def play(input: List[(Cell, Player)]): GameState = {
    def  run(game: Game): ((Cell, Player), Game) = {

      @tailrec
      def updateGame(oldGame: States.Game, curInd: Int): Game = {
        if (curInd == input.length) oldGame
        else {
          val tup = input(curInd)
          if (!oldGame.cells.keySet.contains(tup._1)) {
            val newGame: Game = Game(oldGame.cells + (tup._1 -> tup._2))
            updateGame(newGame, curInd + 1)
          }
          else {
            updateGame(oldGame, curInd + 1)
          }
        }
      }

      val finishGame = updateGame(game, 0)
      ((AA, O), finishGame)
    }
    State(run)
  }

  def result(g: Game): String = {

    if (g.cells.isEmpty)
      standOffMsg

    def checkWin(line: Array[Player]): Boolean = {
      line.count(_ == O) == 3 || line.count(_ == X) == 3
    }

    val winLines = Array(
      g.cells.filter(cell => cell._1.toString.startsWith("A")).values.toArray,
      g.cells.filter(cell => cell._1.toString.startsWith("B")).values.toArray,
      g.cells.filter(cell => cell._1.toString.startsWith("C")).values.toArray,

      g.cells.filter(cell => cell._1.toString.endsWith("A")).values.toArray,
      g.cells.filter(cell => cell._1.toString.endsWith("B")).values.toArray,
      g.cells.filter(cell => cell._1.toString.endsWith("C")).values.toArray,

      g.cells.filter(cell => cell._1.toString.charAt(0) == cell._1.toString.charAt(1)).values.toArray,

      g.cells.filter(cell => cell._1.toString == "AC" || cell._1.toString == "BB" || cell._1.toString == "CA").values.toArray
    ).filter(l => checkWin(l))

    if (winLines.isEmpty || (!winLines.isEmpty && winLines.count(l => l(0) == O) > 0 && winLines.count(l => l(0) == X) > 0))
      standOffMsg
    else
      winMsg(winLines(0)(0))
  }

  def main(args: Array[String]): Unit = {
    val game = Game()

    val p = List((AA, O), (CB, X), (BB, O), (AC, X), (BB, O), (CA, X), (CC, O))

    val (_, gm) = play(p).run(game)

    println(winMsg(O))
    println(result(gm))
    result(gm) == winMsg(O)

    val p2 = List()

    val (_, gm2) = play(p2).run(game)

    println(result(gm2) == standOffMsg)

  }
}
