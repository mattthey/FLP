object Main extends App {
  println("Hello world")
  def isPrime(n: Int): Boolean = n != 1 && (2 until n).forall(n % _ != 0)
}

Main.main(Array.empty)
Main.isPrime(3)