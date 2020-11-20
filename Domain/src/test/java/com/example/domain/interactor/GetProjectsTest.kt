package com.example.domain.interactor

import com.example.domain.executer.PostExecutionThread
import com.example.domain.interactor.browse.GetProjectsUseCase
import com.example.domain.interactor.test.ProjectDataFactory
import com.example.domain.model.Project
import com.example.domain.repository.ProjectRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProjectsTest {
    private lateinit var getProjectsUseCase: GetProjectsUseCase

    @Mock
    lateinit var projectRepository: ProjectRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProjectsUseCase = GetProjectsUseCase(projectRepository, postExecutionThread)
    }

    //Mock data.Assert if onComplete is called
    fun getProjectsCompletes() {
        stubProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObservable = getProjectsUseCase.buildUseCaseObservable().test()
        testObservable.assertComplete()
    }

    //Check if the value carried by the observable is the same thing
    fun getProjectsReturnsData() {
        val projects = ProjectDataFactory.makeProjectList(2)
        stubProjects(Observable.just(projects))
        val testObservable = getProjectsUseCase.buildUseCaseObservable().test()
        testObservable.assertValue(projects)
    }

    private fun stubProjects(observable: Observable<List<Project>>) {
        whenever(projectRepository.getProjects()).thenReturn(observable)
    }

}