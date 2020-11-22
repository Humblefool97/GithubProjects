package com.example.data.store

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectDataStore
import com.example.data.repository.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 *  [ProjectDataStore] implementation for Remote source
 */
class ProjectRemoteDataStore @Inject constructor(private val projectsRemote: ProjectsRemote) :
    ProjectDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("Can't do this remote")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Can't do this remote")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Can't do this remote")
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("Can't do this remote")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("Can't do this remote")
    }

}