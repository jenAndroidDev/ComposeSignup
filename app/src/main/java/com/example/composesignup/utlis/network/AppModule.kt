package com.example.composesignup.utlis.network

import android.content.Context
import androidx.work.WorkManager
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pepul.shops.app.BuildConfig
import com.pepul.shops.app.commons.data.repository.PlaybackAnalyticsRepositoryImpl
import com.pepul.shops.app.commons.data.source.inmemory.InMemoryAnalyticsDataSource
import com.pepul.shops.app.commons.domain.repository.PlaybackAnalyticsRepository
import com.pepul.shops.app.commons.util.Util
import com.pepul.shops.app.commons.util.gson.StringConverter
import com.pepul.shops.app.commons.util.net.AndroidHeaderInterceptor
import com.pepul.shops.app.commons.util.net.DelayInterceptor
import com.pepul.shops.app.commons.util.net.ForbiddenInterceptor
import com.pepul.shops.app.commons.util.net.GuestUserInterceptor
import com.pepul.shops.app.commons.util.net.JwtInterceptor
import com.pepul.shops.app.commons.util.net.PlatformInterceptor
import com.pepul.shops.app.commons.util.net.UserAgentInterceptor
import com.pepul.shops.app.core.Env
import com.pepul.shops.app.core.data.repository.DefaultShopsSharedRepository
import com.pepul.shops.app.core.data.repository.DefaultUserDataRepository
import com.pepul.shops.app.core.domain.repository.ShopsSharedRepository
import com.pepul.shops.app.core.domain.repository.UserDataRepository
import com.pepul.shops.app.core.envForConfig
import com.pepul.shops.app.core.util.DefaultPersistentStore
import com.pepul.shops.app.core.util.JsonParser
import com.pepul.shops.app.core.util.PersistentStore
import com.pepul.shops.app.core.util.PlaybackHealthMonitor
import com.pepul.shops.app.feature.home.data.repository.ProfileCropImageRepository
import com.pepul.shops.app.feature.home.data.source.remote.RetrofitDealsApi
import com.pepul.shops.app.ifDebug
import com.pepul.shops.app.service.PreCacheService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class RepositorySource(val repositorySource: RepositorySources)

enum class RepositorySources { Default, RemoteOnly, }

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.MINUTES)

        okHttpClientBuilder.addInterceptor(UserAgentInterceptor())
        okHttpClientBuilder.addInterceptor(AndroidHeaderInterceptor())
        okHttpClientBuilder.addInterceptor(JwtInterceptor())
        okHttpClientBuilder.addInterceptor(PlatformInterceptor())
        okHttpClientBuilder.addInterceptor(GuestUserInterceptor())
        okHttpClientBuilder.addInterceptor(ForbiddenInterceptor())

        // Add delays to all api calls
        // ifDebug { okHttpClientBuilder.addInterceptor(DelayInterceptor(2_000, TimeUnit.MILLISECONDS)) }

        if (envForConfig(BuildConfig.ENV) == Env.DEV || BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    @WebService
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(String::class.java, StringConverter())
        .create()

    /* Coroutine scope */
    @ApplicationCoroutineScope
    @Singleton
    @Provides
    fun provideApplicationScope(): CoroutineScope =
        Util.buildCoroutineScope(
            coroutineName = Util.APPLICATION_COROUTINE_NAME
        )
    /* END - Coroutine scope */

    @GsonParser
    @Provides
    fun provideGsonParser(gson: Gson): JsonParser
            = com.pepul.shops.app.core.util.GsonParser(gson)

    @Provides
    @Singleton
    fun providePersistentStore(@ApplicationContext application: Context): PersistentStore
        = DefaultPersistentStore.getInstance(application)

    @Provides
    fun providePlaybackAnalyticsRepository(): PlaybackAnalyticsRepository {
        return PlaybackAnalyticsRepositoryImpl(InMemoryAnalyticsDataSource())
    }

    @Provides
    fun provideWorkManager(@ApplicationContext application: Context): WorkManager {
        return WorkManager.getInstance(application)
    }

    @Provides
    fun providePreCacheService(@ApplicationContext application: Context): PreCacheService {
        return PreCacheService.getInstance(application)
    }

    @Provides
    @Singleton
    fun provideProfileCropImageRepository(
        @ApplicationContext appContext: Context,
        retrofitDealsApi: RetrofitDealsApi
    ): ProfileCropImageRepository {
        return ProfileCropImageRepository(appContext, retrofitDealsApi)
    }

    @Provides
    fun providePlaybackHealthMonitor(@ApplicationContext context: Context): PlaybackHealthMonitor {
        return PlaybackHealthMonitor.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideReviewManager(@ApplicationContext appContext: Context): ReviewManager =
        ReviewManagerFactory.create(appContext)

    @Provides
    @Singleton
    fun provideAppUpdateManager(@ApplicationContext application: Context): AppUpdateManager =
        AppUpdateManagerFactory.create(application)
}

@Module
@InstallIn(SingletonComponent::class)
interface AppBinderModule {

    @Binds
    fun bindsUserDataRepository(
        userDataRepository: DefaultUserDataRepository
    ): UserDataRepository

    @Binds
    fun bindsShopsSharedRepository(
        shopsSharedRepository: DefaultShopsSharedRepository
    ): ShopsSharedRepository
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GsonParser

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationCoroutineScope

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WebService