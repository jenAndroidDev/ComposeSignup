package com.example.composesignup.core

import android.content.Context
import com.example.composesignup.core.sessionManager.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(
    SingletonComponent::class
)
object AppModule {

    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context:Context) = SessionManager(context)
}