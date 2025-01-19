package com.example.composesignup.core.di

import android.app.Application
import com.example.composesignup.utlis.PersistentHelper
import com.example.composesignup.utlis.PersistentStore

class AppDependenciesProvider(private val application: Application):AppDependencies.Provider {
    override fun providePersistentStore(): PersistentStore {
        return PersistentHelper.getInstance(application)
    }
}