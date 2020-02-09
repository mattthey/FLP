class Vehicle(description: String) {
  println("constructing...")
  val name = description + " vehicle"
  def print(): Unit = println(name)
}

class Car extends Vehicle("heavy")

val car = new Car


car.print()