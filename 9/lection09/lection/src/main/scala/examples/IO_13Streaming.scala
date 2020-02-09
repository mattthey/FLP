package examples

import examples.IO_09.IO

object IO_13Streaming {

  def lines(filename: String): IO[Stream[String]] = IO {
    val src = io.Source.fromFile(filename)
    src.getLines.toStream append {
      src.close; Stream.empty
    }
  }

}
