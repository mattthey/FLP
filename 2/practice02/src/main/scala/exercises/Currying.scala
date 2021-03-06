package exercises

//  while, return и var запрещены
//  0.5 балла
object Currying {

  def curry[A, B, C](f: (A, B) => C): A => (B => C) = (a: A) => ((b: B) => f(a, b))

  def uncurry[A, B, C](f: A => (B => C)): (A, B) => C = (a: A, b: B) => f(a)(b)

}