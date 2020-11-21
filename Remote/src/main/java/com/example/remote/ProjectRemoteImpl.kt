package com.example.remote

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsRemote
import com.example.remote.mapper.ProjectModelMapper
import com.example.remote.service.ApiService
import io.reactivex.Observable
import javax.inject.Inject

class ProjectRemoteImpl @Inject constructor(
    private val service: ApiService,
    private val mapper: ProjectModelMapper
) : ProjectsRemote {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepo("language:kotlin", "stars", "desc")
            .map {
                it.items.map { projectModel ->
                    mapper.mapFromModel(projectModel)
                }
            }
    }
}