package com.example.domain.interactor.test

import com.example.domain.model.Project
import java.util.*

object ProjectDataFactory {
    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun makeProject(): Project {
        return Project(
            randomUuid(), randomUuid(), randomUuid(),
            randomUuid(), randomUuid(), randomUuid(),
            randomUuid()
        )
    }

    fun makeProjectList(count: Int): List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }
}