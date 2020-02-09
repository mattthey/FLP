trait Полет {
  def крылья: String
  def лететь(): String =
    "Хлопаю моими " + крылья
}

trait Птица {
  def крылья: String =
    "крыльями"
}

class Голубь extends Птица with Полет
class Пингвин extends Птица

class Дракон extends Полет {
  def крылья: String =
    "стеклянными крыльями"
}

object Икар extends Полет {
  def крылья: String =
    "восковыми крыльями"
}

Икар.лететь()