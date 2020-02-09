package examples

object Func04DefaultArgs {

  def distance(speed: Float, time: Float = 600): Float =
    speed * time

  def int42 = 42

  distance(0.1f)

  distance(time = int42, speed = 0.1f)

}
