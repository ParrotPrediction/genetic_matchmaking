import com.khozzy.ts.Algorithm
import com.khozzy.ts.Population

println "Tech Saturday Simulation ...."

Population population = new Population(100)
def popFitness = population.getFitness()
Algorithm.evolvePopulation(population)

println "Done, fitness: ${popFitness}"