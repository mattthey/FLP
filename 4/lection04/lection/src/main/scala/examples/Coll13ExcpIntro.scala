package examples

object Coll13ExcpIntro {

  def failingFn(i: Int): Int = {
    val y: Int = throw new Exception("fail!")
    try {
      val x = 42 + 5
      x + y
    }
    catch { case e: Exception => 43 }
  }
//  scala> failingFn(12)
//  java.lang.Exception: fail!
//    at .failingFn(<console>:11)
//    ... 36 elided


  def failingFn2(i: Int): Int = {
    try {
      val x = 42 + 5
      x + ((throw new Exception("fail!")): Int)
    }
    catch { case e: Exception => 43 }
  }

//  scala> failingFn2(12)
//  res2: Int = 43

}
