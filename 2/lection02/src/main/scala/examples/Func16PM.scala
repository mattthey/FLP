package examples

object Func16PM {

  val patMch1: Int => Int = {
    case i if (i > 0) => i * 2
  }

  //  { case p1 => b1 … case pn => bn }
  val patMchN: (Int, Byte, Double, String) => Int = {
    case (i, b, d, s) if (i > 0) => i * 2
    case _ =>  42
  }

  patMchN(-3, 4, 5.0, "")

}
