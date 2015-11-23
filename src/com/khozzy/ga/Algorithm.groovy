package com.khozzy.ga

class Algorithm {

    static final def uniformRate = 0.5
    static final def mutationRate = 0.015
    static final def tournamentSize = 5
    static final def elitism = true

    static def evolvePopulation(pop) {
        def newPopulation = new Population(pop.individuals.size())

        // Keep our best individual
        if (elitism) {
            newPopulation.individuals[0] = pop.getFittest()
        }

        // Crossover population
        def elitismOffset = elitism ? 1 : 0

        // Loop over the population size and create new individuals with crossover
        for (i in elitismOffset ..< pop.individuals.size()) {
            def indiv1 = tournamentSelection(pop)
            def indiv2 = tournamentSelection(pop)
            def newIndiv = crossover(indiv1, indiv2)

            newPopulation.individuals[i] = newIndiv
        }

        // Mutate population
        for (i in elitismOffset ..< pop.individuals.size()) {
            mutate(newPopulation.individuals[i])
        }

        return newPopulation
    }

    private static def crossover(indiv1, indiv2) {
        def newSol = new Individual()

        // loop through genes
        for (i in 0 ..< indiv1.genes.size()) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.genes[i] = indiv1.genes[i]
            } else {
                newSol.genes[i] = indiv2.genes[i]
            }
        }

        return newSol
    }

    private static def mutate(indiv) {
        for (i in 0 ..< indiv.genes.size()) {
            if (Math.random() <= mutationRate) {
                indiv.genes[i] = (byte) Math.round(Math.random())
            }
        }
    }

    private static def tournamentSelection(pop) {
        def tournament = new Population(tournamentSize)

        // for each place in the tournament get a random individual
        for (i in 0 ..< tournamentSize) {
            def randomId = (int) (Math.random() * pop.individuals.size())
            tournament.individuals[i] = pop.individuals[randomId]
        }

        return tournament.getFittest()
    }
}
