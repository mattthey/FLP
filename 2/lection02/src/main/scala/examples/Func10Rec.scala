package examples

import scala.annotation.tailrec

object Func10Rec {

  def factorialCycle(n: Int): Int = {
    var res = 1
    var cnt = 1
    while (cnt <= n) {
      res = res * cnt
      cnt += 1
    }
    res
  }


//  @tailrec
  def factorial1(n: Int): Int =
    if (n == 0) 1
    else factorial1(n - 1) * n


//  @tailrec
  def factorial2(n: Int): Int =
    if (n == 0) 1
    else n * factorial2(n - 1)


  def factorial3(n: Int) = {
    @tailrec
    def factorialAcc(acc: Int, n: Int): Int =
      if (n == 0) acc
      else factorialAcc(acc * n, n - 1)

    factorialAcc(1, n)
    println("OK")
  }

  def main(args: Array[String]): Unit = {
    println(factorial3(3))
  }

}
