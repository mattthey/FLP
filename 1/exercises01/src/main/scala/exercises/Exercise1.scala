package exercises

object Exercise1 {

  //Создайте класс Сoordinates, который содержит 2 числовых поля и
  //переопределите в нем метод toString таким образом, чтобы
  //он возвращал строку вида: (Число, Число)
  //Входные данные: числа 4 и 2

  class Coordinates(val x: Int, val y: Int) {

    def this() {
      this(0,0)
    }

    def this(s: String) {
      this(s.slice(0,1).toInt, s.slice(2,3).toInt)
    }

    //    override def toString: String = s"(${x}, ${y})"
    override def toString: String = "(" + x + ", " + y + ")"
  }

  def main(args: Array[String]): Unit = {
//    val input = scala.io.StdIn.readLine()
//    val vector1 = new Coordinates(input)
//    println(vector1.x)
//    println(vector1)
//
//    val vector2 = new Coordinates()
  }
}