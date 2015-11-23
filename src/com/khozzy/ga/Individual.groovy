package com.khozzy.ga

import groovy.transform.ToString

@ToString
class Individual {
    def static defaultGeneLength = 64
    def genes = []
    def fitness = 0 // cache

    def generateIndividual() {
        for (i in 0 ..< defaultGeneLength) {
            def gene = (byte) Math.round(Math.random())
            genes[i] = gene
        }
    }

    def getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this)
        }

        return fitness
    }
}
