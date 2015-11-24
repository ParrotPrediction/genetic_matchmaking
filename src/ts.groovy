import com.khozzy.ts.Algorithm
import com.khozzy.ts.Population

println "Tech Saturday Simulation ...."

Population population = new Population(50)
println "Initial population fitness: ${population.getOverallFitness()}"

for (generation in 2..500) {
    population = Algorithm.evolvePopulation(population)
    println "Gen: ${generation}, fitness: ${population.getOverallFitness()}"
}