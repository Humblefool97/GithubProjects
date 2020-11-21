package com.example.data.repository

import com.example.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsRemote {
    fun getProjects(): Observable<List<ProjectEntity>>
}