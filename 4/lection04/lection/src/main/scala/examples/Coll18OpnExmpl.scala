package examples

object Coll18OpnExmpl {

  val optInt = Some(4)

  optInt.filter(_ > 5)
//  res0: Option[Int] = None
  optInt.filter(_ > 3)
//  res2: Option[Int] = Some(4)

  optInt.filter(_ > 5).orElse(Some(0))
//  res3: Option[Int] = Some(0)

  optInt.filter(_ > 5).getOrElse(0)
//  res4: Int = 0
  optInt.filter(_ > 5).get
//  java.util.NoSuchElementException: None.get
//  at scala.None$.get(Option.scala:366)
//  at scala.None$.get(Option.scala:364)
//  ... 36 elided


  optInt.map(_.toString)
//  res6: Option[String] = Some(4)

  optInt.flatMap(i => Some(i * 2.0))
//  res7: Option[Double] = Some(8.0)
  optInt.filter(_ > 5).flatMap(i => Some(i * 2.0))
//  res8: Option[Double] = None

  for{
    i <- Some(1)
    j <- Some(2)
    k <- Some(3)
  } yield (i + j + k)


  for{
    i <- Some(1)
    j <- Some(2)
    k: Option[Int] <- None
  } yield (i + j + k)

}
