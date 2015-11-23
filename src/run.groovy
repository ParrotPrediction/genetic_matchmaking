import com.khozzy.ga.Algorithm
import com.khozzy.ga.Population

Population myPop = new Population(50);

def generationCount = 0
def maxGenerations = 50

while (generationCount < maxGenerations) {
    generationCount++
    println "Generation: ${generationCount} Fittest: ${myPop.getFittest().getFitness()}"
    myPop = Algorithm.evolvePopulation(myPop)
}

println "\n** Solution found **"
println "Generation: ${generationCount}"
println "Genes: ${myPop.getFittest()}"
