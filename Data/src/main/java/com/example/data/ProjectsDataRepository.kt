package com.example.data

import com.example.data.mapper.ProjectMapper
import com.example.data.repository.ProjectsCache
import com.example.data.store.ProjectDataStoreProvider
import com.example.domain.model.Project
import com.example.domain.repository.ProjectRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 *  Implements Domain's [ProjectRepository]
 */
open class ProjectsDataRepository @Inject constructor(
    private val mapper: ProjectMapper,
    private val cache: ProjectsCache,
    private val provider: ProjectDataStoreProvider
) : ProjectRepository {

    override fun getProjects(): Observable<List<Project>> {
        return provider.getDataStore(false ,false).getProjects()
            .flatMap { projects ->
                provider.getProjectCacheDataStore()
                    .saveProjects(projects)
                    .andThen(Observable.just(projects))
            }
            .map { it ->
                it.map {
                    mapper.mapFromEntity(it)
                }
            }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return provider.getProjectCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return provider.getProjectCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkedProjectProjects(): Observable<List<Project>> {
        return provider.getProjectCacheDataStore().getBookmarkedProjects().map {
            it.map { projectEntity ->
                mapper.mapFromEntity(projectEntity)
            }
        }
    }

}