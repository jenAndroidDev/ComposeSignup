package com.example.composesignup.core.di

import android.content.Context
import androidx.compose.ui.tooling.preview.Preview
import com.example.composesignup.core.sessionManager.SessionManager
import com.example.composesignup.feature.onboard.domain.usecase.EmailValidatorUseCase
import com.example.composesignup.feature.onboard.domain.usecase.InputFormUseCase
import com.example.composesignup.feature.onboard.domain.usecase.UserNameValidatorUseCase
import com.example.composesignup.utlis.PersistentHelper
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

    @Provides
    @Singleton
    fun providePersistentStore(@ApplicationContext context: Context) = PersistentHelper.getInstance(application = context)

    @Provides
    @Singleton
    fun provideInputFormUseCase() = InputFormUseCase(
        userEmailUseCase = EmailValidatorUseCase(),
        userNameUseCase = UserNameValidatorUseCase()
    )
}