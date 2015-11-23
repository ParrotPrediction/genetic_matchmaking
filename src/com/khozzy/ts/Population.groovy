package com.khozzy.ts

class Population {
    final static def MATCH_MAX = 3

    def individuals = []

    Population(size) {
        individuals = new Participant[size]

        for (i in 0 ..< size) {
            Participant newIndividual = new Participant(i)
            newIndividual.generateRandom()
            individuals[i] = newIndividual
        }

        assignRandomly()
    }

    def getFitness() {
        def fitness = 0

        for (i in individuals) {
            fitness += i.fitness
        }

        return fitness
    }

    def getMaxFitness() {
        def maxFitness = 0

        for (i in individuals) {
            for (n in i.needs) {
                maxFitness++
            }
        }

        return maxFitness
    }

    private def assignRandomly() {

        for (i in individuals) {
            while (i.matches.size() < MATCH_MAX) {
                def randomId = (int) (Math.random() * individuals.size())

                if (!(randomId in i.matches) && (randomId != i.id)) {
                    i.matches.add(randomId)
                }
            }

            i.calculateFitness(this)
        }
    }
}
