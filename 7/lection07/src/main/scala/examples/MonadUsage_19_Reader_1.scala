package examples

object MonadUsage_19_Reader_1 extends App {

  import MonadUsage_19_Reader._

  case class Dog(name: String, favoriteFood: String)

  val reader = readerMonad[Dog]

  val dogName: Reader[Dog, String] =
    Reader(d => d.name)

  println(dogName.run(Dog("Bobik", "Fleisch")))
  //Bobik

  val greetDoggy: Reader[Dog, String] = dogName.map(name => s"Hallo ${name}")

  val feedDoggy: Reader[Dog, String] = Reader(cat => s"Geniessen Sie ein Stueck ${cat.favoriteFood}")

  val greetAndFeed: Reader[Dog, String] =
    for {
      greet <- greetDoggy
      feed <- feedDoggy
    } yield s"$greet. $feed."

  println(greetAndFeed.run(Dog("Bobik", "Fleisch")))
  //Hallo Bobik. Geniessen Sie ein Stueck Fleisch.

  println(greetAndFeed.run(Dog("Barbos", "Knochen")))
  //Hallo Barbos. Geniessen Sie ein Stueck Knochen.

}
