package examples

object Coll02List6 {

  val list = List(1, 2, 3, 4)

  val list2 = 5 :: 6 :: 7 :: 8 :: Nil

  list ::: list2 == list.foldRight(list2)(_ :: _)
//  res1: Boolean = true

}
