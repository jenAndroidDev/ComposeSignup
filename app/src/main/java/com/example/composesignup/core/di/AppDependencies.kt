package com.example.composesignup.core.di

import android.app.Application
import com.example.composesignup.utlis.PersistentStore

object AppDependencies {

    @Volatile
    private var application:Application?=null

    @Volatile
    private var provider:Provider?= null

    @Volatile
    var persistentStore:PersistentStore?=null
        get() = field

    @Synchronized
    fun init(application: Application,provider: Provider){
        synchronized(this){
            if (AppDependencies.application != null || AppDependencies.provider != null) {
                throw IllegalStateException("Already initialized!")
            }
            persistentStore = provider.providePersistentStore()
        }
    }


    interface Provider{
        fun providePersistentStore():PersistentStore
    }
}