package chromosome

abstract class Chromosome[T]() extends Ordered[Chromosome[T]]{
  
  lazy val sequence : Seq[T] = randomize
  
  lazy val fitness : Double = evalFitness
  
  def evalFitness : Double
  
  def mutate : Chromosome[T]
  
  def randomize : Seq[T]
  
  def +(other : Chromosome[T]) : Chromosome[T]
  
  def compare(other : Chromosome[T]) = 
    this.fitness.compare(other.fitness)
  
}


