package com.example.mobile_ui.di

import android.app.Application
import com.example.mobile_ui.GitHubTrendingApplication
import com.example.mobile_ui.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
AppModule::class,
UiModule::class,
PresentationModule::class,
DataModule::class,
CacheModule::class,
RemoteModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: GitHubTrendingApplication)
}