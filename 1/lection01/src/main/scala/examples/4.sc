trait Pet {
  type Voice
  def voice: Voice
}
class Cat extends Pet {
  type Voice = String
  def voice = "meow-meow"
}
class Robot extends Pet {
  type Voice = Int
  def voice = 31337
}

new Robot().voice