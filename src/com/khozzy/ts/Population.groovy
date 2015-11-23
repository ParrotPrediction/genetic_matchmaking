package com.khozzy.ts

class Population {
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

    private def assignRandomly() {
        //TODO: Assign people to each other randomly
    }
}
