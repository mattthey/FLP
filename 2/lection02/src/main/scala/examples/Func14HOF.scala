package examples

object Func14HOF {

  def sumSquares(args: Int*): Int =
    args.map(x => x * x).sum

  def sumCubes(args: Int*): Int =
    args.map(x => x * x * x).sum

  def sumFactorials(args: Int*): Int = {

    def fact(n: Int, acc: Int): Int =
      if (n == 0) 1 else fact(n - 1, acc * n)

    args.map(x => fact(x, 1)).sum

  }


  def sum(f: Int => Int, args: Int*): Int =
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

  sum(squares, nums: _*)
  sum(cubes, nums: _*)
  sum(factorials, nums: _*)
}
