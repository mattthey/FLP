package examples


import examples.IO_10._

object IO_12File {


  trait Files[A]
  case class OpenRead(file: String) extends Files[HandleR] {
    def handler: HandleR = ???
  }
  case class OpenWrite(file: String) extends Files[HandleW] {
    def handler: HandleW = ???
  }
  case class ReadLine(h: HandleR) extends Files[Option[String]] {
    def value: Option[String] = ???
  }
  case class WriteLine(h: HandleW, line: String) extends Files[Unit]

  trait HandleR
  trait HandleW

  def fahrenheitToCelsius: String => String = ???

  def loop(f: HandleR, c: HandleW): IO[_] = for {
    line <- Suspend { () => ReadLine(f) }
    _ <- line.value match {
      case None => IO.unit(())
      case Some(s) => Suspend {() =>
        WriteLine(c, fahrenheitToCelsius(s))
      } flatMap[Unit](_ => IO.unit(loop(f, c)))
    }
  } yield ()


  def convertFiles = for {
    f <- Suspend(() => OpenRead("fahrenheit.txt"))
    c <- Suspend(() => OpenWrite("celsius.txt"))
    _ <- loop(f.handler,c.handler)
  } yield ()
}
