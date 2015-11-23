package com.khozzy.ts

import groovy.transform.ToString

@ToString
class Participant {

    final static def PICK_SKILL_PROB = 0.2
    final static def PICK_NEED_PROP = 0.4

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
            if (!(value in skills) && (Math.random() <= PICK_NEED_PROP)) {
                needs.add(value)
            }
        }
    }

    def getFitness() {
        //TODO: Implement me
        return fitness
    }
}
