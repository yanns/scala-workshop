// example of what NOT TO DO
trait AlwaysWarm extends Drink {
  override def isWarm: Boolean = true
}

trait AlwaysCold extends Drink {
  override def isWarm: Boolean = false
}

sealed trait Drink {
  // abstract
//  def isWarm: Boolean

  // with implementation
  def isWarm: Boolean =
    this match {
      case Coffee(_) ⇒ true
      case ClubMate() ⇒ false
      case Cola() ⇒ false
    }
}
case class Coffee(withMilk: Boolean) extends Drink with AlwaysWarm
case class ClubMate() extends Drink
case class Cola() extends Drink with AlwaysCold

def isWarm(drink: Drink): Boolean = {
  drink match {
    case Coffee(_) ⇒ true
    case ClubMate() ⇒ false
    case Cola() ⇒ false
  }
}

isWarm(Coffee(true))
isWarm(Coffee(withMilk = true))
isWarm(Coffee(withMilk = false))
isWarm(ClubMate())
isWarm(Cola())

Cola().isWarm
