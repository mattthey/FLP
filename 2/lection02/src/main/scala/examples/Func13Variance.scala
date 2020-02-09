package examples

object Func13Variance {

  sealed trait Vehicle
  class Bike extends Vehicle
  class Car extends Vehicle

  class IParking[T](vehicles: List[T]) {
    def park(vehicle: T): IParking[T] = ???

    def checkVehicles(conditions: String): List[T] = ???

    def flatMap[S](f: Function1[T, IParking[S]]): IParking[S] = ???
  }

  class CParking[+T](vehicles: List[T]) {
//    def _park_(vehicle: T): CParking[T] = ???
    def park[S >: T](vehicle: S): CParking[S] = ???

    def checkVehicles[T](conditions: String): List[T] = ???

    def flatMap[S](f: Function1[T, CParking[S]]): CParking[S] = ???
  }

  class XParking[-T](vehicles: List[T]) {
    def park(vehicle: T): XParking[T] = ???

//    def _checkVehicles(conditions: String): List[T] = ???
    def checkVehicles[S <: T](conditions: String): List[S] = ???

//    def _flatMap[S](f: T => XParking[S]): XParking[S] = ???
    def flatMap[R <: T, S](f: Function1[R, XParking[S]]): XParking[S] = ???
  }

}
