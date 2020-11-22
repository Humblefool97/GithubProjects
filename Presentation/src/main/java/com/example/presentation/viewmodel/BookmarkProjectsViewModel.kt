package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactor.bookmark.GetBookmarkedProjects
import com.example.domain.model.Project
import com.example.presentation.mapper.ProjectViewMapper
import com.example.presentation.model.ProjectView
import com.example.presentation.state.Resource
import com.example.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BookmarkProjectsViewModel @Inject constructor(
    private val getBookmarkedProjects: GetBookmarkedProjects,
    private val mapper: ProjectViewMapper
) : ViewModel() {

    val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    override fun onCleared() {
        getBookmarkedProjects.dispose()
        super.onCleared()
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getBookmarkedProjects.execute(ProjectsSubscriber())
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

        override fun onError(e: Throwable) {
            liveData.postValue(
                Resource(
                    ResourceState.ERROR, null,
                    e.localizedMessage
                )
            )
        }

        override fun onComplete() {}

    }
}