import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global

val f1 = Future.apply {
  // evil Thread.sleep
  Thread.sleep(10)
  "result of the future f1"
}

val f2 = Future("f2")
val f3 = Future.successful("f3")
val f4 = Future.failed(new Exception("f4"))

// throw an exception in the main thread
//val f5 = Future.successful {
//  throw new Exception("f5")
//}

// result = Future.failed
val f6 = Future {
  throw new Exception("f6")
}

val p7 = Promise[String]()
val f7 = p7.future
new Thread(new Runnable {
  override def run(): Unit = {
    p7.success("hello")
//    p7.failure(new Exception("hi!"))
  }
}).run()
val f7b = p7.future

// only for test
import scala.concurrent.Await
import scala.concurrent.duration._

val atMost: Duration = 3.seconds
Await.result(f1, atMost)
Await.result(f2, atMost)
Await.result(f3, atMost)
Await.result(f7, atMost)
Await.result(f7b, atMost)
