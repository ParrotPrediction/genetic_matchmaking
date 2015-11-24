import com.khozzy.ts.Population

println "Tech Saturday Simulation ...."

Population population = new Population(100)

def popFitness = population.getFitness()

println "Done, fitness: ${popFitness}"