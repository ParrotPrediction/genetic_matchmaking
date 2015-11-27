package com.khozzy.ts

class Algorithm {

    static final def MUTATION_PROB = 0.0003

    static def evolvePopulation(Population pop) {
        def newPopulation = (Population) pop.clone()

        crossover(newPopulation)
        mutate(newPopulation)

        return newPopulation
    }

    private static def crossover(Population pop) {
        def indiv1 = (Participant) pop.getRandomIndividual()
        def indiv2 = (Participant) pop.getRandomIndividual()

        if (indiv1 != indiv2) {

            if (indiv1.isUseful(indiv2)) {

                def alternatives = []

                indiv1.matches.eachWithIndex { match, index ->
                    def altPop = (Population) pop.clone()
                    altPop.match(indiv1.id, indiv2.id, index)

                    alternatives << ([index: index, fitness: altPop.fitness()])
                }

                alternatives.sort { -it.fitness }
                def bestAlt = alternatives.first()

                if (bestAlt.fitness > pop.fitness()) {
                    pop.match(indiv1.id, indiv2.id, bestAlt.index)
                }
            }
        }
    }

    private static def mutate(Population pop) {

        if (Math.random() < MUTATION_PROB) {

            def indiv1 = (Participant) pop.getRandomIndividual()
            def indiv2 = (Participant) pop.getRandomIndividual()
            def randomIndex = (int) (Math.random() * indiv1.matches.size())

            if (indiv1 != indiv2) {
                pop.match(indiv1.id, indiv2.id, randomIndex)
            }
        }
    }

}
