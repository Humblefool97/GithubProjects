package com.example.data.repository

import com.example.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Has the combined functinality of Projects cache and Remote
 */
interface ProjectDataStore {

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun clearProjects(): Completable

}