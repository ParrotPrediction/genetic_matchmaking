package com.khozzy.ts

import groovy.transform.ToString

@ToString
class Participant implements Serializable {

    final static def PICK_SKILL_PROB = 0.2
    final static def PICK_NEED_PROB = 0.4

    def id
    def skills = []
    def needs = []
    def matches = []

    def fitness = 0

    Participant(id) {
        this.id = id
        this.skills = []
        this.needs = []
        this.matches = []
    }

    def generateRandom() {

        // Generate skills
        for (value in Skill.values()) {
            if (Math.random() <= PICK_SKILL_PROB) {
                skills.add(value)
            }
        }

        // Generate needs
        for (value in Skill.values()) {
            if (!(value in skills) && (Math.random() <= PICK_NEED_PROB)) {
                needs.add(value)
            }
        }
    }

    def isUseful(Participant individual) {
        for (need in this.needs) {
            if (need in individual.skills) {
                return true
            }
        }
        return false
    }

    /**
     * Calculates how well is an individual fitted.
     * The final value is a weighted mean:
     *
     * fitness = 0.7 * needs + 0.3 * popularity
     *
     * Higher value means better adjustment
     *
     * @param population
     */
    def calculateFitness(Population population) {
        this.fitness = (0.7 * needsFulfilled(population)) + (0.3 * popularity(population))
    }

    /**
     * Check whether individual needs are fulfilled.
     *
     * @param population
     * @return value between 0 (no needs) and 1 (all needs redeemed)
     */
    private def needsFulfilled(Population population) {

        // If participant has no needs he is happy
        if (needs.size() == 0) {
            return 1
        }

        def needsFulfilled = 0

        // Iterate through the needs
        for (n in needs) {

            // Iterate through matches
            for (m in matches) {
                def possibleMatch = (Participant) population.individuals[m]

                if (n in possibleMatch.skills) {
                    needsFulfilled++
                    break
                }
            }
        }

        return needsFulfilled / needs.size()
    }

    /**
     * Check how often the person is being recommended to others.
     * It might happen that due to some unique skill specific individual will
     * be recommended many times. The constrain might help to balance the distribution
     * of matching.
     *
     * The assumption is made that it gets much harder to talk with more than the number
     * of people specified by the parameter (by default 3).
     *
     * The penalty is applied for any extra person.
     *
     * @param population
     * @return value between 0 (is desired by everyone) to 1 (comfort of delivering help)
     */
    private def popularity(Population population, talkLimit = 3) {
        def popularityCounter = 0

        // Iterate through all individuals
        for (i in population.individuals) {
            if (id in i.matches) {
                popularityCounter++
            }
        }

        if (popularityCounter < talkLimit) {
            popularityCounter = 0
        }

        return 1 - (popularityCounter / population.individuals.size())
    }
}
