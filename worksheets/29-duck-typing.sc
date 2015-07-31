// duck typing
def withClose(
  closeAble: { def close(): Unit },
  op: { def close(): Unit } => Unit): Unit = {

  try {
    op(closeAble)
  } finally {
    closeAble.close()
  }
}

class Connection {
  def close() = println("close connection")
}

val connection = new Connection
withClose(connection, c =>
  println ("do something with Connection")
)
