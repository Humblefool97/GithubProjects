package com.example.domain.interactor.bookmark

import com.example.domain.executer.PostExecutionThread
import com.example.domain.interactor.BaseObservableUseCase
import com.example.domain.model.Project
import com.example.domain.repository.ProjectRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetBookmarkedProjects @Inject constructor(
    private val projectRepository: ProjectRepository,
    postExecutionThread: PostExecutionThread
) : BaseObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectRepository.getBookmarkedProjectProjects()
    }
}