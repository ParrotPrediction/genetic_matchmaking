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

    /**
     * Calculates how well is population fitted
     *
     * @return value between 0 (poor matching) and 1 (best matching)
     */
    def getFitness() {
        def fitness = 0

        for (i in individuals) {
            fitness += i.fitness
        }

        return fitness / individuals.size()
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
