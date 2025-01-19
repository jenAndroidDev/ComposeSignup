package com.example.composesignup.utlis

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class PersistentHelper(
    private val preferences:SharedPreferences
) :PersistentStore {

    override val isUserLoggedIn: Boolean
        get() = TODO("Not yet implemented")

    override val signUpStep: Int
        get() = TODO("Not yet implemented")

    override fun setSignUpStatus(step: Int) {
        TODO("Not yet implemented")
    }

    override fun setUserLoginStatus(logged: Boolean) {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    companion object{

        @Volatile private var INSTANCE:PersistentStore?=null

        @Synchronized
        fun getInstance(application: Context):PersistentStore =
            INSTANCE?: synchronized(this){ INSTANCE?: createEncryptedInstance(application) }

        private fun createEncryptedInstance(application: Context):PersistentStore{

            val masterKey = MasterKey.Builder(application
            , SECURED_KEY_ALIAS)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            return PersistentHelper( EncryptedSharedPreferences(
                context = application,
                fileName = "compose_signup_secured_pref",
                masterKey = masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            ))
        }
        private const val APP_PREFERENCE_NAME = "compose_signup_app"
        private const val SECURED_KEY_ALIAS = "compose_signup_secured_store"

        object UserPreferencesKey{

        }
        object AppEssentialKeys{
            const val WELCOME_SCREEN_SHOWED = "welcome_screen_showed"
        }

    }



}