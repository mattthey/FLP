package exercises

import java.security.cert.PKIXRevocationChecker

import scala.annotation.tailrec


object TailRec {

  //  На вход дан список чисел
  //  числа последовательно складываются
  //  необходимо найти число, которое первый раз повторно встречается в рез-те суммирования
  //  если список кончается, а число не найдено, в конец списка дописываем исходный список и продолжаем вычисления
  //  рез-том функции должно первое число, которое встречается дваджы впервые
  //  нпр дан список List(1, -1)
  //  первым значением, встретившимся дважды будет 0
  //  1 + (-1) | 0
  //  0 + 1    | 1
  //  1 + (-1) | 0

  //  должна быть использована аннотация @tailrec
  //  while и var запрещены
  //  подумайте об исключениях
  //  try catch не использовать
  //  2 балла

  def secondOccurrence(inp: List[Int]): Option[Int] = {
    @tailrec
    def searchOccurrence(inp: List[Int], setSums: Set[Int], pos: Int, sum: Int): Int = {
      if(setSums.contains(sum + inp(pos % inp.length)))
        sum + inp(pos % inp.length)
      else
        searchOccurrence(inp, setSums.union(Set(sum + inp(pos % inp.length))),
          pos + 1, sum + inp(pos % inp.length))
    }

    if(inp.isEmpty)
      None
    else
      Option(searchOccurrence(inp, Set(), 0, 0))
  }

  def main(args: Array[String]): Unit = {
    val l = List(7, 7, -2, -7, -4)
    val l2 = List(1, -1)
    val l3 = List(-21,4,7,4,8,3,6,4,8)
    val l4 = List(1)
    println(secondOccurrence(l2))
  }
/*
  def secondOccurrence(inp: List[Int]): Option[Int] = {
    def nextIndex(i: Int): Int = (i + 1) % inp.length

    @tailrec
    def searchOccurrence(listSums: List[Int], index: Int): Option[Int] = {
      val curSum = inp(index) + listSums.head

      if (listSums.contains(curSum))
        Option[Int](curSum)
      else {
        val newListSums = curSum :: listSums
        searchOccurrence(newListSums, nextIndex(index))
      }
    }

    if (inp.length > 1)
     searchOccurrence(List[Int](inp.head + inp(1)), nextIndex(1))
    else if (inp.nonEmpty)
      Option(inp.head)
    else
      None
  }
*/
}
