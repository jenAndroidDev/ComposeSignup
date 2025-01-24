package com.example.composesignup

import android.app.Application
import com.example.composesignup.core.di.AppDependencies
import com.example.composesignup.core.di.AppDependenciesProvider
import com.example.composesignup.utlis.AppStartup
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ComposeSignUp:Application() {

    override fun onCreate() {
        super.onCreate()
            Timber.plant(Timber.DebugTree())

        AppStartup.getInstance()
            .addBlocking("app-dependencies", this::initApplicationDependencies)
            .execute()
    }
    private fun initApplicationDependencies(){
        AppDependencies.init(
            application = this,
            provider = AppDependenciesProvider(this)
        )
    }
}