package examples

object Func03NamedArgs {

  def distance(speed: Float, time: Float): Float =
    speed * time


  distance(0, 0)
  distance(speed = 0.1f, time = 0.2f)
  distance(time = 0.2f, speed = 0.1f)

}
