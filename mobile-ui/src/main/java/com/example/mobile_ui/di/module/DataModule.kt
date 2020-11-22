package com.example.mobile_ui.di.module

import com.example.data.ProjectsDataRepository
import com.example.domain.repository.ProjectRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {
    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectRepository
}