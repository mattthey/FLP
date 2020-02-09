package examples

import scala.collection.immutable.Range

object Coll07Range {

  val range1 = 1 to 100

  val range3 = 0 until 3
  val range4 = Range(0, 3)


  val range5 =  0 to (100, 9)
  //0 9 18 27 36 45 54 63 72 81 90 99

  val range6 =  0 until (100, 3)
  //0 3 6 9 12 15 18 21 24 27 30 33 36 39 42 45 48 51 54 57 60 63 66 69 72 75 78 81 84 87 90 93 96 99

}
