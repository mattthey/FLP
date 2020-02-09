package examples

import examples.IO_09.IO

object IO_12Streaming {

  def linesGt40k(filename: String): IO[Boolean] = IO {
    val src = io.Source.fromFile(filename)
    try {
      var count = 0
      val lines: Iterator[String] = src.getLines
      while (count <= 40000 && lines.hasNext) {
        lines.next
        count += 1
      }
      count > 40000
    }
    finally src.close
  }


  val lines: Stream[String] = Stream()

  lines.zipWithIndex.exists(_._2 + 1 >= 40000)

}
