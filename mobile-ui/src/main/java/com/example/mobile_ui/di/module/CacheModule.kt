package com.example.mobile_ui.di.module

import android.app.Application
import com.example.cache.CacheProjectsImpl
import com.example.cache.db.AppDatabase
import com.example.data.repository.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDatabase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: CacheProjectsImpl): ProjectsCache
}
