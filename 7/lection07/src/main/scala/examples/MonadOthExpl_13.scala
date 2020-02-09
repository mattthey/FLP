package examples

object MonadOthExpl_13 extends App {

  def parseInt(str: String): Option[Int] =
    scala.util.Try(str.toInt).toOption

  def divide(a: Int, b: Int): Option[Int] =
    if (b == 0) None else Some(a / b)

  def stringDivideBy(aStr: String, bStr: String): Option[Int] =
    parseInt(aStr).flatMap { aNum =>
      parseInt(bStr).flatMap { bNum =>
        divide(aNum, bNum)
      }
    }

  stringDivideBy("6", "2")
  //Some(3)

  stringDivideBy("6", "0")
  // None

  stringDivideBy("6", "foo")
  //None

  stringDivideBy("bar", "2")
  //None

}
