package examples

import scala.io.StdIn._
import scala.util.Try

object IO_23App {

  def parseInt(s: String): Option[Int] = Try(s.toInt).toOption

  case class IO[A](unsafeRun: () => A) { self =>
    def map[B](f: A => B): IO[B] = IO(() => f(self.unsafeRun()))

    def flatMap[B](f: A => IO[B]): IO[B] = IO(() => f(self.unsafeRun()).unsafeRun())
  }

  def putStrLn(line: String): IO[Unit] = IO(() => println(line))
  def getStrLn: IO[String] = IO(() => readLine())

    def main: IO[Unit] = {

      def nextInt(upper: Int): IO[Int] = IO(() => scala.util.Random.nextInt(5) + 1)

      def gameLoop(name: String): IO[Unit] =
        for {
          num   <- nextInt(5).map(_ + 1)
          _     <- putStrLn("Dear " + name + ", please guess a number from 1 to 5:")
          input <- getStrLn
          _     <- parseInt(input).fold(putStrLn("You did not enter a number")
                    )(guess =>
                      if (guess == num) putStrLn("You guessed right, " + name + "!")
                      else putStrLn("You guessed wrong, " + name + "! The number was: " + num))
          // ....
        } yield ()


      var exec = true

      while (exec) {

//....

        putStrLn("Do you want to continue, " + ??? + "?")

        readLine() match {
          case "y" => exec = true
          case "n" => exec = false
        }
      }

      for {
        _ <- putStrLn("What is your name?")
        name <- getStrLn
        _ <- putStrLn("Hello, " + name + ", welcome to the game!")
        _ <- gameLoop(name)
      } yield ()


    }
}
