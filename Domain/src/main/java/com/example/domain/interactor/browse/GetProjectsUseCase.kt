package com.example.domain.interactor.browse

import com.example.domain.executer.PostExecutionThread
import com.example.domain.interactor.BaseObservableUseCase
import com.example.domain.model.Project
import com.example.domain.repository.ProjectRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetProjectsUseCase @Inject constructor(
    private val projectRepository: ProjectRepository,
    postExecutionThread: PostExecutionThread
) :
    BaseObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    //Perform task
    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectRepository.getProjects()
    }
}