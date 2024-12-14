package com.example.composesignup

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ComposeSignUp:Application() {

    override fun onCreate() {
        super.onCreate()
            Timber.plant(Timber.DebugTree())
    }
}