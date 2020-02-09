package examples

import examples.IO_10.IO

object IO_11Main {

  def main(args: Array[String]): Unit = {
    IO.run(pureMain(args))

  }

  def pureMain(args: IndexedSeq[String]): IO[Unit] = ???


}
