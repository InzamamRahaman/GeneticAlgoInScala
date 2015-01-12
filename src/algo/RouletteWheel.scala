package algo

class RouletteWheel[T](members : IndexedSeq[T], values : IndexedSeq[Double]) {
  
  val cumulativeSum = computeCumulativeSum(values)
  
  val table = cumulativeSum.map {x => x / cumulativeSum.last} zipWithIndex
  
  def computeCumulativeSum(xs : IndexedSeq[Double]) = 
    xs.scanLeft(0.0)(_ + _)
  
  def getMemberFromMembers = {
    val randomNum = Math.random()
    val pair = table dropWhile {case (value, idx) => (value < randomNum)} head
    val idx = pair._2
    (members(idx), idx)
  }
  
  def getOtherMember(idx : Int) = {
    var res = this.getMemberFromMembers
    while(res._2 == idx)
      res = this.getMemberFromMembers
    res
  }

}