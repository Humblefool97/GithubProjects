package com.example.mobile_ui.di.module

import com.example.data.repository.ProjectsRemote
import com.example.mobile_ui.BuildConfig
import com.example.remote.ProjectRemoteImpl
import com.example.remote.service.ApiService
import com.example.remote.service.NetworkFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideApiService(): ApiService {
            return NetworkFactory.provideRetrofitApiService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(remoteImpl: ProjectRemoteImpl): ProjectsRemote
}