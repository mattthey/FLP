package exercises
//  while, return и var запрещены
//  try catch не использовать
//  1 балл

object Poly {

  type Set[T] = T => Boolean

  def contains[T](s: Set[T], elem: T): Boolean = s(elem)

  def singletonSet[T](elem: T): Set[T] = (x: T) =>x == elem

  def union[T](s: Set[T], t: Set[T]): Set[T] = (x: T) => contains(s, x) || contains(t, x)

  def intersect[T](s: Set[T], t: Set[T]): Set[T] = (x: T) => contains(s, x) && contains(t, x)

  def diff[T](s: Set[T], t: Set[T]): Set[T] = (x: T) => contains(s, x) && !contains(t, x)

  def filter[T](s: Set[T], p: T => Boolean): Set[T] = (x: T) => p(x) && s(x)

}