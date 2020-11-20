package com.example.data.store

import com.example.data.repository.ProjectDataStore
import javax.inject.Inject

/**
 *  Provides [projectRemoteDataStore] &/or [projectsCacheDataStore] based on the requirement
 */
class ProjectDataStoreProvider @Inject constructor(
    private val projectsCacheDataStore: ProjectsCacheDataStore,
    private val projectRemoteDataStore: ProjectRemoteDataStore
) {

    open fun getDataStore(projectsCached: Boolean, projectCacheExpired: Boolean): ProjectDataStore {
        return if (projectsCached && !projectCacheExpired) projectsCacheDataStore else projectRemoteDataStore
    }

    //Specifically needed for some operations like saving the projects to cache
    open fun getProjectCacheDataStore() = projectsCacheDataStore
}