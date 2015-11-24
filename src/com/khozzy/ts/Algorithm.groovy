package com.khozzy.ts

import java.util.concurrent.ThreadLocalRandom

class Algorithm {

    static final def UNIFORM_RATE = 0.5

    static def evolvePopulation(Population pop) {
        def newPopulation = new Population(pop.individuals.size())

        // Elitism
        // keep 5-10% of all best matches
        pop.individuals = pop.individuals.sort { -it.fitness }
        def percentage = ThreadLocalRandom.current().nextInt(5, 10 + 1)
        def elitismOffset = (int) (percentage * pop.individuals.size() / 100)

        for (i in 0 ..< elitismOffset) {
            newPopulation.individuals[i] = pop.individuals[i]
        }

        // Crossover
        for (i in elitismOffset ..< pop.individuals.size()) {
            def indiv = pop.individuals[i]
            def randomIndiv = randomSelection(pop)

            if (indiv != randomIndiv) {
                indiv = crossover(indiv, randomIndiv, pop)
                newPopulation.individuals[i] = indiv
            }
        }

        // Mutation
        for (i in elitismOffset ..< pop.individuals.size()) {
            //mutate(newPopulation.individuals[i])
        }

        newPopulation.recalculateFitness()
        return newPopulation
    }

    private static def crossover(indiv, randomIndiv, population) {
        indiv = (Participant) indiv
        randomIndiv = (Participant) randomIndiv

        //print "Doing crossover between ${indiv.id} and ${randomIndiv.id}"

        if (randomIndiv.id in indiv.matches) {
           print " +"
            // There is a useless match - remove it
            if (!indiv.isUseful(randomIndiv) && Math.random() <= UNIFORM_RATE) {
                print "*"
                indiv.matches - randomIndiv.id
            }

        } else {
            print " -"
            // No matching already, but might be promising - add it
            if (indiv.isUseful(randomIndiv) && Math.random() <= UNIFORM_RATE) {
                print "*"
                indiv.addMatching(randomIndiv, population)
            }
        }
       // println ''

        return indiv
    }

    //TODO: Mutate
    private static def mutate(indiv) {
    }

    private static def randomSelection(Population pop) {
        def randomId = (int) (Math.random() * pop.individuals.size())
        return pop.individuals[randomId]
    }

}
