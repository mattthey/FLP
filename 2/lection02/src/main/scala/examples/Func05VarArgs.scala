package examples

object Func05VarArgs {

  def echo(args: String*) =
    for (arg <- args) println(arg)

  echo()

  echo("one")

  echo("hello", " world")

  val numbers = List("one", "two", "three")

  //echo(numbers)

  echo(numbers: _*)

}
