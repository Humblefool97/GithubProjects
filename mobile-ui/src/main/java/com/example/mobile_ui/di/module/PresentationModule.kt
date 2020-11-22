package com.example.mobile_ui.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_ui.ViewModelFactory
import com.example.presentation.viewmodel.BookmarkProjectsViewModel
import com.example.presentation.viewmodel.ProjectsViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProjectsViewModel::class)
    abstract fun bindBrowseProjectsViewModel(viewModel: ProjectsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookmarkProjectsViewModel::class)
    abstract fun bindBrowseBookmarkedProjectsViewModel(
        viewModel: BookmarkProjectsViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)