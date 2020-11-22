package com.example.mobile_ui

import android.app.Application
import timber.log.Timber

class GitHubTrendingApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
    }
    private fun setUpTimber(){
        Timber.plant(Timber.DebugTree())
    }
}