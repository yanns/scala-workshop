import java.util.UUID

import scala.util.control.NonFatal

case class Transaction(id: String = UUID.randomUUID().toString) {
  def commit() = println(s"commit transaction $id")
  def rollback() = println(s"rollback transaction $id")
}

def withTransaction(block: Transaction => Unit) = {
  val transaction = Transaction()
  try {
    block(transaction)
    transaction.commit()
  } catch {
    case t: Throwable =>
      println(s"error with transaction $transaction: " + t.getMessage)
      transaction.rollback()
  }
}

def withdrawFrom(bankAccount: String, sum: Double)(implicit transaction: Transaction): Unit = {
  require(Math.abs(sum) <= 100, "can only withdraw sums up to 100 euros")
  println(s"[transaction $transaction] withdraw $sum euros from $bankAccount")
}

def withdrawTo(bankAccount: String, sum: Double)(implicit transaction: Transaction): Unit = {
  require(Math.abs(sum) <= 100, "can only withdraw sums up to 100 euros")
  println(s"[transaction $transaction] withdraw $sum euros to $bankAccount")
}

def transfer(from: String, to: String, sum: Double): Unit = {
  withTransaction { implicit transaction =>
    withdrawFrom(from, -sum)
    withdrawTo(to, sum)
  }
}

transfer("bob", "alice", 25)