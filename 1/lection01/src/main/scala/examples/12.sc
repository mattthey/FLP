def square(x: Int): Int = x * x

def complexStuff(x: Int) = {
  def multiply(m: Int) = x * m
  def increase(x: Int) = x + 500

  increase(multiply(1000))
}

complexStuff(100)