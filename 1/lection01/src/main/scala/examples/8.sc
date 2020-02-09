def square(x: Int): Int = x * x
val cude = { x: Int => x * x * x }

val numbers = List(1, 2, 3)
numbers.map(square)

def mul(m: Int): (Int => Int) = { x => x * m }

val double = mul(2)
double(6)