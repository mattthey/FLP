package examples

object Func15Currying {

  def sum(f: Int => Int)(args: Int*): Int =
    args.map(f).sum

  val squares: Int => Int = x => x * x
  val cubes: Int => Int = x => x * x * x
  val factorials: Int => Int =
    n => {
      def fact(n: Int, acc: Int): Int =
        if (n == 0) 1 else fact(n - 1, acc * n)

      fact(n, 1)
    }

  val nums = List(1, 2, 3, 4)

  //sum(squares, nums: _*)

  val sumSquares = sum(squares) _
  val sumCubes = sum(cubes) _
  val sumFactorials = sum(factorials) _

  sumSquares(nums)
  sumCubes(nums)
  sumFactorials(nums)


  //sum: (f: Int => Int)(args: Int*)Int
  //(Int => Int) => ((Int*) => Int)
  //public static int sum(scala.Function1<java.lang.Object, java.lang.Object>, scala.collection.Seq<java.lang.Object>);

  def main(args: Array[String]): Unit = {
    val input = scala.io.StdIn.readLine().toInt
  }
}
