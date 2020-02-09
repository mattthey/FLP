package examples

object Func01Pure extends App {

  object ImpureCafe {

    trait CreditCard {
      def charge(price: Double): Unit = ???
    }

    case class Coffee(price: Double = 1.0)

    class Cafe {
      def buyCoffee(cc: CreditCard): Coffee = {
        val cup = new Coffee()
        cc.charge(cup.price)
        cup
      }
    }

  }


  object ABitBetterCafe {

    trait CreditCard

    case class Coffee(price: Double = 1.0)

    trait Payments {
      def charge(cc: CreditCard, p: Double): Unit = ???
    }

    class Cafe {
      def buyCoffee(cc: CreditCard, p: Payments): Coffee = {
        val cup = new Coffee()
        p.charge(cc, cup.price)
        cup
      }
    }

  }


  object FunctionalCafe {

    trait CreditCard

    case class Coffee(price: Double = 1.0)

    case class Charge(cc: CreditCard, amount: Double) {
      def combine(other: Charge): Charge =
        if (cc == other.cc)
          Charge(cc, amount + other.amount)
        else
          throw new Exception("Can't combine charges to different cards")
    }

    class FunctionalCafe {
      def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
        val cup = new Coffee()
        (cup, Charge(cc, cup.price))
      }
    }

  }

}
