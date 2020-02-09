package examples

object Coll03Set {

  //val Set = collection.immutable.Set

  val nums = Set(1, 2, 3)

  nums + 5
  nums - 3

  nums ++ Set(5, 6)
  nums ++ List(5, 6)

  val p = (nums | Set(1, 2, 0)) == nums.union(Set(1, 2, 0))

  val p2 = (nums & Set(1, 2, 0)) == nums.intersect(Set(1, 2, 0))

  nums.size

  nums.contains(3)

}
