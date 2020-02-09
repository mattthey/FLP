package examples

object Coll04Map {

  //val Map = collection.immutable.Map

  val nums = Map("i" -> 1, "ii" -> 2)
  nums + ("vi" -> 6)

  nums - "ii"

  nums ++ List("iii" -> 3, "v" -> 5)
  nums -- List("i", "ii")
  nums.size
  nums.contains("ii")
  nums("ii")
  nums.keys
  nums.keySet
  nums.values
  nums.isEmpty


}
