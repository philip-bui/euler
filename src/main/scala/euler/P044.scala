package main.scala.euler

object P044 {

  def pentagonal(): Int = {
    var i: Int = 0
    var p1: Int = 0
    var p2: Int = 0
    while (true) {
      i+= 1
      p2 = genPentagonal(i)

      for (j <- i - 1 until 0 by -1) {
        p1 = genPentagonal(j)
        if (isPentagonal(p2 - p1) && isPentagonal(p2 + p1)) {
          return p2 - p1
        }
      }
    }
    0
  }

  /**
    * Inverse function to check if N is a Pentagon Number.
    * The reason this works is that between P(n) and P(n-1),
    * the difference increases relative to n.
    * E.g p(2) - p(1) = 4. p(3) - p(2) = 7.
    * If n is not a pentagonal number, going through the inverse function will not return a Natural number.
    * @param n Number to check for Pentagonal property.
    * @return If inverse function returns a Natural Number
    */
  def isPentagonal(n: Int): Boolean = {
    var penTest = (Math.sqrt(1 + 24 * n) + 1.0) / 6.0
    penTest == penTest.toInt
  }

  def pentagonalBrute(): Int = {
    var i: Int = 0
    var p2, p1: Int = 0
    var sum: Int = 0
    var max: Int = 0
    var maxI: Int = 1
    var pentagonals: Map[Int, Int] = Map()

    while (i < 100) {
      i+= 1
      if (pentagonals.contains(i)) {
        p2 = pentagonals(i)
      } else {
        p2 = genPentagonal(i)
        pentagonals += (i -> p2)
      }

      for (j <- i - 1 until 0 by -1) {
        // Theoretically previous Pentagons should have been generated by i.
        p1 = pentagonals(j)
        if (pentagonals.contains(p2 - p1)) {
          sum = p2 + p1
          while (max < sum) {
            max = genPentagonal(maxI)
            pentagonals += (maxI -> max)
            maxI += 1
          }
          if (pentagonals.contains(sum)) {
            return p2 - p1
          }
        }
      }
    }
    println(pentagonals.mkString(","))
    0
  }

  def genPentagonal(i: Int): Int = {
    i * (3 * i - 1) / 2
  }
}
