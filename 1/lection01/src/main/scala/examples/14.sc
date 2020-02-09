class Vehicle(description: String) {
  def print(): Unit =
    println(description)
}

object Vehicle {
  def produce(): Vehicle =
    new Vehicle("from factory")
}

Vehicle.produce().print()

object Car extends Vehicle("heavy")
Car.print()