import scala.annotation.tailrec

def factorial1(n: BigInt): BigInt =
  if (n == 0) 1 else n * factorial1(n - 1)

factorial1(4)
//factorial1(10000)


def factorial2(n: BigInt): BigInt = {
  @tailrec
  def loop(acc: BigInt, m: BigInt): BigInt = {
    if (m == 0) acc else loop(acc * m, m - 1)
  }
  loop(1, n)
}

factorial2(4)
factorial2(10000)
