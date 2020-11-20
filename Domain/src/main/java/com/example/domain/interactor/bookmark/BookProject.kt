package com.example.domain.interactor.bookmark

import com.example.domain.executer.PostExecutionThread
import com.example.domain.interactor.BaseCompletableUseCase
import com.example.domain.repository.ProjectRepository
import io.reactivex.Completable
import javax.inject.Inject

class BookmarkProject @Inject constructor(
    private val projectRepository: ProjectRepository,
    postExecutionThread: PostExecutionThread
) : BaseCompletableUseCase<BookmarkProject.Params>(postExecutionThread) {


    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }

    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null)
            throw IllegalStateException("params can't be null")
        return projectRepository.bookmarkProject(params.projectId)
    }
}