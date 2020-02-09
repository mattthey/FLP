package examples

import scala.util.Random

object Func18GenRand {

  val rnd = Random.nextInt

  val x = 3 * rnd

  val y = 3 * Random.nextInt

  x == y
  // Bang!!!

}
