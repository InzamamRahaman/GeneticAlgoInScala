package algo

abstract class CompletionStatus[+T] 

case class Finished[+T](elem : T) extends CompletionStatus[T]
case class NotFinished[+T](elem : T) extends CompletionStatus[T]