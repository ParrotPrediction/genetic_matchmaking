package com.khozzy.ts

import groovy.transform.AutoClone
import groovy.transform.AutoCloneStyle

@AutoClone(style = AutoCloneStyle.SERIALIZATION)
class Population implements Serializable {

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
    def fitness() {
        def fitness = 0

        individuals.each { i ->
            i.calculateFitness(this)
            fitness += i.fitness
        }

        return fitness / individuals.size()
    }

    /**
     * Returns a random individual from the population
     *
     * @return Participant
     */
    def getRandomIndividual() {
        def randomId = (int) (Math.random() * individuals.size())
        return individuals[randomId]
    }

    def match(indiv1id, indiv2id, position) {
        Participant indiv1 = getIndividualById(indiv1id)

        if (indiv1.matches.size() < MATCH_MAX) {
            indiv1.matches << indiv2id
        } else {
            indiv1.matches[position] = indiv2id
        }
    }

    private def getIndividualById(id) {
        return individuals.find { i ->
            i.id == id
        }
    }

    private def assignRandomly() {
        for (i in individuals) {
            while (i.matches.size() < MATCH_MAX) {
                def randomId = (int) (Math.random() * individuals.size())

                if (!(randomId in i.matches) && (randomId != i.id)) {
                    i.matches << randomId
                }
            }
        }
    }
}
