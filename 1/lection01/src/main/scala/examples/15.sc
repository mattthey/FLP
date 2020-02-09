val elements =
  List("fire","water","earth","air")

elements.size
elements(1)

elements(2) = "darkness"

var newElements =
  List("fire","water","earth","air")
newElements =
  newElements.updated(2, "darkness")