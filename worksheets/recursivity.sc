import scala.annotation.tailrec

def factorial1(n: BigInt): BigInt =
  if (n == 0) 1 else n * factorial1(n - 1)


factorial1(3)
factorial1(10)
//factorial(10000)




def factorial2(n: BigInt): BigInt = {
  @tailrec
  def loop(acc: BigInt, n: BigInt): BigInt = {
    if (n == 0) acc
    else loop(acc * n, n - 1)
  }

  loop(1, n)
}

factorial2(3)
factorial2(10)
factorial2(10000)
