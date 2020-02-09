package exercises

object Exercise3 {

  //У нас появилась задача делать арифметические операции над числами начинающихся с 0 и с плавающей точкой
  //Мы хотим уметь делать вот так: Number("000123.7") + Number("009856.3") и получать Number = 246.0
  //Реализуйте правильно класс, функции и подсказка pretty syntax: реализуйте объект компаньон
  //Входные данные: Number("000123.9") + Number("009876.3")

  class Number(val value: Double) {

    def this(strValue: String) {
      this(strValue.toDouble)
    }

    def +(r: Number): Number = new Number(this.value + r.value)

    def -(r: Number): Number = new Number(this.value - r.value)

    def *(r: Number): Number = new Number(this.value * r.value)

    def /(r: Number): Number = new Number(this.value / r.value)

    override def toString: String = value.toString
  }

  object Number {
    def apply(x: String): Number = new Number(x)
    def apply(x: Double): Number = new Number(x)
    def fromNumber(num: Number): String = num.toString
  }

  def main(args: Array[String]): Unit = {
    println(Number("000123.9") + Number("009876.3"))
    println(Number("000123.9") - Number("009876.3"))
    println(Number("000123.9") * Number("009876.3"))
    println(Number("000123.9") / Number("009876.3"))
  }
}

/*
Объекты-компаньоны

Для каждого класса можно объявить специальный объект-компаньон (companion object). Это объект объявленный в том же файле и с тем же именем, что и класс. Этот объект имеет доступ к private полям класса.

Возможно вы уже встречались с созданием некоторых объектов без использования ключевого слова new. Здесь нет магии, это вызов метода apply на объекте-компаньоне
*/
/*

class Number() {

    def +(r: Number): Number = ???

    def -(r: Number): Number = ???

    def *(r: Number): Number = ???

    def /(r: Number): Number = ???
  }

  object Number {
    def apply(x: String): Number = ???
    def fromNumber(num: Number): String = ???
  }

*/