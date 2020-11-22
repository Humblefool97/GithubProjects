package com.example.mobile_ui.di.module

import com.example.domain.executer.PostExecutionThread
import com.example.mobile_ui.BrowseActivity
import com.example.mobile_ui.UiThread
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {
    @Binds
    abstract fun bindPostExecutorThread(uiThread: UiThread):PostExecutionThread

    @ContributesAndroidInjector
    abstract fun bindBrowseActivity() : BrowseActivity

}