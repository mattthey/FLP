package exercises

object Exercise4 {

  //Число Армстронга - натуральное число, которое равно сумме своих цифр, возведённых в степень,
  // равную количеству его цифр.
  //Например:
  //11 не число Армстронга, потому что 11 != 1^1 + 1^1 != 2
  //153 число Армстронга, потому что: 153 = 1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153
  //Напишите некоторый код, чтобы определить, является ли число числом Армстронга.
  //нельзя использовать var, while, .asInctanceOf(), регулярные выражения, .split()
  //можно использовать scala.math
  //Входные данные: 11(нет) и 153(да)

  import scala.math.pow

  def SumCubesDigit(num: String, len: Int): Double = num.map(x => pow(x.asDigit, len)).sum

  def isArmNum(n: Int): Boolean = n == SumCubesDigit(n.toString, n.toString.length)

  def main(args: Array[String]): Unit = {
    println(isArmNum(153))
    println(isArmNum(11))
    println(isArmNum(912985153))
  }
}