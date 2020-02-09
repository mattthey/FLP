package examples

import scala.io.StdIn._
import scala.util.Try

object IO_22App {

  def parseInt(s: String): Option[Int] = Try(s.toInt).toOption

  case class IO[A](unsafeRun: () => A) { self =>
    def map[B](f: A => B): IO[B] = IO(() => f(self.unsafeRun()))

    def flatMap[B](f: A => IO[B]): IO[B] = IO(() => f(self.unsafeRun()).unsafeRun())
  }

  def putStrLn(line: String): IO[Unit] = IO(() => println(line))
  def getStrLn: IO[String] = IO(() => readLine())

    def main: IO[Unit] = {

      var exec = true

      while (exec) {
        val num = scala.util.Random.nextInt(5) + 1

        putStrLn("Dear " + ??? + ", please guess a number from 1 to 5:")

        val guess = readLine().toInt

        if (guess == num) putStrLn("You guessed right, " + ??? + "!")
        else putStrLn("You guessed wrong, " + ??? + "! The number was: " + num)

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

      } yield ()
    }
}
