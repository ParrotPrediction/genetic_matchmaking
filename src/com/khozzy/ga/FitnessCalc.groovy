package com.khozzy.ga

class FitnessCalc {

    static def getFitness(Individual individual) {
        def fitness = 0

        for (int i = 0; i < individual.genes.size(); i++) {
            fitness += Math.random()
        }

        return fitness
    }

}
