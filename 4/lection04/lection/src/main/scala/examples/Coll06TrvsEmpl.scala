package examples

object Coll06TrvsEmpl {

  val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  list filter (_ % 2 == 0)

  list dropWhile (_ < 5)

  list.foldLeft[(String, Int)](("", 0)){ (b: (String, Int), n: Int) =>
    (b._1 + ("m: " + b._2 + " n: " + n + " m + n: " + (b._2 + n) +"\n"), b._2 + n)
  }

  /*
      m: 0 n: 1 m + n: 1
      m: 1 n: 2 m + n: 3
      m: 3 n: 3 m + n: 6
      m: 6 n: 4 m + n: 10
      m: 10 n: 5 m + n: 15
      m: 15 n: 6 m + n: 21
      m: 21 n: 7 m + n: 28
      m: 28 n: 8 m + n: 36
      m: 36 n: 9 m + n: 45
      m: 45 n: 10 m + n: 55
   */


  list.foldRight[(String, Int)](("", 0)){ (n: Int, b: (String, Int)) =>
    (b._1 + ("m: " + b._2 + " n: " + n + " m + n: " + (b._2 + n) +"\n"), b._2 + n)
  }

  /*
      m: 0 n: 10 m + n: 10
      m: 10 n: 9 m + n: 19
      m: 19 n: 8 m + n: 27
      m: 27 n: 7 m + n: 34
      m: 34 n: 6 m + n: 40
      m: 40 n: 5 m + n: 45
      m: 45 n: 4 m + n: 49
      m: 49 n: 3 m + n: 52
      m: 52 n: 2 m + n: 54
      m: 54 n: 1 m + n: 55
   */

  list.reduceLeft(_ + _)

  list.take(4)

  list.drop(4)

}
