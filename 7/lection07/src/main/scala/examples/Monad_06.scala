package examples

object Monad_06 {

  def parseInt(str: String): Option[Int] =
    scala.util.Try(str.toInt).toOption

  def intDivide(a: Int, b: Int): Option[Int] =
    if (b == 0) None else Some(a / b)

  def stringDivide(aStr: String, bStr: String): Option[Int] =
    parseInt(aStr).flatMap { aNum =>
      parseInt(bStr).flatMap { bNum =>
        intDivide(aNum, bNum)
      }
    }

  stringDivide("6", "2")
  // res1: Option[Int] = Some(3)

  stringDivide("6", "0")
  // res2: Option[Int] = None

  stringDivide("6", "string")
  // res3: Option[Int] = None

  stringDivide("string", "2")
  // res4: Option[Int] = None

}
