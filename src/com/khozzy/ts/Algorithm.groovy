package com.khozzy.ts

import java.util.concurrent.ThreadLocalRandom

class Algorithm {

    static def evolvePopulation(Population pop) {
        def newPopulation = new Population(pop.individuals.size())

        // Elitism
        // keep 5-10% of all best matches
        pop.individuals = pop.individuals.sort { -it.fitness }
        def percentage = ThreadLocalRandom.current().nextInt(5, 10 + 1)
        def elitismOffset = percentage * pop.individuals.size() / 100

        for (i in 0 ..< elitismOffset) {
            newPopulation.individuals[i] = pop.individuals[i]
        }

        // Crossover
        for (i in elitismOffset ..< pop.individuals.size()) {
            //def indiv1 = tournamentSelection(pop)
            //def indiv2 = tournamentSelection(pop)
            //def newIndiv = crossover(indiv1, indiv2)

            //newPopulation.individuals[i] = newIndiv
        }

        // Mutation
        for (i in elitismOffset ..< pop.individuals.size()) {
            //mutate(newPopulation.individuals[i])
        }

        return newPopulation
    }

    // TODO: Crossover
    private static def crossover(indiv1, indiv2) {
        return null
    }

    //TODO: Mutate
    private static def mutate(indiv) {
    }

    // TODO: Tournament Selection
    private static def tournamentSelection(pop) {
        return null
    }

}
