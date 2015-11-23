package com.khozzy.ga

class Population {
    def individuals = []

    // Create population
    Population(populationSize) {
        individuals = new Individual[populationSize]

        for (i in 0..<populationSize) {
            Individual newIndividual = new Individual()
            newIndividual.generateIndividual()
            individuals[i] = newIndividual
        }
    }

    def getFittest() {
        def fittest = individuals[0]

        for (i in 0 ..< individuals.size()) {
            if (fittest.getFitness() <= individuals[i].getFitness()) {
                fittest = individuals[i]
            }
        }

        return fittest
    }
}
