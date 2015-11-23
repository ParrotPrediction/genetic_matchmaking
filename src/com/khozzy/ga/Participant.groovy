package com.khozzy.ga

import groovy.transform.ToString

@ToString
class Participant {

    def id
    def skills = []
    def needs = []
    def matches = []

    Participant(id, skills, needs) {
        this.id = id
        this.skills = skills
        this.needs = needs
        this.matches = []
    }
}
