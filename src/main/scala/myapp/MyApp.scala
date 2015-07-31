package myapp

class DB {
  def query(): String = "my query"
}

class MyService(db: DB) {
  def myBusinessImplementation = "call db and get: " + db.query()
}

class MyController(myService: MyService) {
  def getHome = myService.myBusinessImplementation
}

trait MyModul1 {
  def db: DB
  lazy val myController = new MyController(myService)
  lazy val myService = new MyService(db)
}

trait RealDb {
  lazy val db = {
    // add shutdown hook
    new DB
  }
}

trait MyApplication
  extends MyModul1
  with RealDb

object MyApp extends App with MyApplication {
  println(myController.getHome)
}
