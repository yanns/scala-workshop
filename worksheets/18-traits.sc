{
  trait Drink
  case class Coffee(withMilk: Boolean) extends Drink
  case class ClubMate() extends Drink

  val coffee = Coffee(withMilk = false)
  val clubMate = ClubMate()
}

{
  trait Drink {
    def isWarm: Boolean
  }
  case class Coffee(withMilk: Boolean) extends Drink {
    override def isWarm: Boolean = true
  }
  case class ClubMate() extends Drink {
    override def isWarm: Boolean = false
  }

  val coffee = Coffee(withMilk = false)
  val clubMate = ClubMate()

  coffee.isWarm
  clubMate.isWarm
}
{
  trait Drink
  case class Coffee(withMilk: Boolean) extends Drink
  case class ClubMate() extends Drink

  val coffee = Coffee(withMilk = false)
  val clubMate = ClubMate()

  def isWarm(d: Drink): Boolean = d match {
    case Coffee(_) => true
    case ClubMate() => false
  }

  isWarm(coffee)
  isWarm(clubMate)
}

trait AlwaysWarm extends Drink {
  override def isWarm() = {
    println("warm on AlwaysWarm")
    true
  }
}

sealed trait Drink {
  def isWarm(): Boolean = this match {
    case Coffee(_) => true
    case ClubMate() => false
    case Water() => false
  }
}
case class Coffee(withMilk: Boolean) extends Drink with AlwaysWarm
case class ClubMate() extends Drink
case class Water() extends Drink

val coffee = Coffee(withMilk = false)
val clubMate = ClubMate()
val water = Water()

val coffee2 = Coffee(withMilk = false)
coffee == coffee2

coffee.isWarm()
clubMate.isWarm()
water.isWarm()
