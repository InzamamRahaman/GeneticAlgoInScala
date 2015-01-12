package algo

import chromosome._

class Population[T](n : Int,
    mutation : Double, crossover : Double, f : () => Chromosome[T], 
    g : Population[T] => GoodnessChecker[T]) {

  type U = Chromosome[T]
  private var population = randomizePopulation(n)
  val m_prob = mutation
  val c_prob = crossover +  mutation
  val checker = g(this)
  
  def getPopulation = population
  
  def randomizePopulation(num : Int) = 
    (1 to num).map { _ => f()}
    
  def evaluatePopulation = 
    population.map { x => x.fitness }
  
  def sortChromosomes = 
    population.sortWith {case (ch1, ch2) => ch1 < ch2}
  
  def pickBestChromosome = 
    population.maxBy { ch1 => ch1.fitness }
  
  def selectOperation(wheel : RouletteWheel[U])  = {
    val rnum = Math.random()
    val chromosome = wheel.getMemberFromMembers
    if(rnum <= m_prob) chromosome._1.mutate
    else if (rnum <= c_prob) crossoverChromosomes(chromosome, wheel)
    else chromosome._1
  }
  
  def crossoverChromosomes(chromosome : (U, Int), wheel : RouletteWheel[U]) = {
    val other = wheel.getOtherMember(chromosome._2)
    chromosome._1 + other._1
  }
  
  def generateNewPopulation = {
    val wheel = new RouletteWheel(population, evaluatePopulation)
    val arr = Vector[U]()
    arr.+:(pickBestChromosome)
    val nLessOne = n - 1
    val temp = (1 to nLessOne) map {_ => selectOperation(wheel)}
    population = arr.toIndexedSeq.++:(temp)
  }
  
 
  
  def run = {
    while(checker.isGoodEnough == false) {
      generateNewPopulation
    }
    pickBestChromosome
  }
  
    
  
}