import com.khozzy.ts.Algorithm
import com.khozzy.ts.Population

final def POPULATION_SIZE = 20
final def GENERATIONS = 3000
final def RESULTS_EVERY_N = 500

Population population = new Population(POPULATION_SIZE)
println "Initial population fitness: ${population.fitness()}\n"

for (generation in 2..GENERATIONS) {
    population = Algorithm.evolvePopulation(population)

    if (!(generation % RESULTS_EVERY_N)) {
        println "Gen: ${generation}, fitness: ${population.fitness()}"
    }
}

println "\nDone.\nFinal fitness: ${population.fitness()}"