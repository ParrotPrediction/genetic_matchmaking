import com.khozzy.ts.Population

println "Tech Saturday Simulation ...."

Population population = new Population(100)

def popFitness = population.getFitness()
def maxFitness = population.getMaxFitness()

println "Done, quality: ${popFitness/maxFitness*100}%"