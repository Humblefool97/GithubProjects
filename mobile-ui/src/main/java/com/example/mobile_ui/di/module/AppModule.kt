package com.example.mobile_ui.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun bindsContext(application: Application): Context
}