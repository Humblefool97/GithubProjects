package com.example.mobile_ui

import android.app.Activity
import android.app.Application
import com.example.mobile_ui.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class GitHubTrendingApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        setUpTimber()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    private fun setUpTimber() {
        Timber.plant(Timber.DebugTree())
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }
}