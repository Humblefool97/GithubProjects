package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactor.bookmark.BookmarkProject
import com.example.domain.interactor.browse.GetProjectsUseCase
import com.example.domain.model.Project
import com.example.presentation.mapper.ProjectViewMapper
import com.example.presentation.model.ProjectView
import com.example.presentation.state.Resource
import com.example.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class ProjectsViewModel @Inject constructor(
    private val getProjectsUseCase: GetProjectsUseCase?,
    private val bookmarkProject: BookmarkProject,
    private val mapper: ProjectViewMapper
) : ViewModel() {
    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    init {
        fetchProjects()
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getProjectsUseCase?.execute(ProjectsSubscriber())
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun bookmarkProject(projectId: String) {
        return bookmarkProject.execute(
            BookmarkProjectsSubscriber(),
            BookmarkProject.Params.forProject(projectId)
        )
    }


    override fun onCleared() {
        getProjectsUseCase?.dispose()
        super.onCleared()
    }

    inner class ProjectsSubscriber : DisposableObserver<List<Project>>() {
        override fun onNext(t: List<Project>) {
            liveData.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    t.map { mapper.fromMapToView(it) }, null
                )
            )
        }

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }

    inner class BookmarkProjectsSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    liveData.value?.data, null
                )
            )
        }

        override fun onError(e: Throwable) {
            liveData.postValue(
                Resource(
                    ResourceState.ERROR, liveData.value?.data,
                    e.localizedMessage
                )
            )
        }

    }
}