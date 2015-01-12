package algo

abstract class GoodnessChecker[T](pop : Population[T]) {

  def isGoodEnough : Boolean
  
}