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
        get() = getAppPreferences().getBoolean(UserPreferencesKey.IS_LOGGED_IN,false)

    override val signUpStep: Int
        get() = getAppPreferences().getInt(AppEssentialKeys.SIGN_UP_STEP,0)?:-1

    override val email: String
        get() = getAppPreferences().getString(UserPreferencesKey.USER_EMAIL,"")?:""

    override val name: String
        get() = getAppPreferences().getString(UserPreferencesKey.USER_NAME,"")?:""

    override val password: String
        get() = getAppPreferences().getString(UserPreferencesKey.PASSWORD,"")?:""

    override val isWelcomeScreenShown: Boolean
        get() = getAppPreferences().getBoolean(AppEssentialKeys.WELCOME_SCREEN_SHOWED,false)

    override fun setUserName(name: String) {
        getAppPreferences().edit().putString(UserPreferencesKey.USER_NAME,name).apply()
    }

    override fun setUserEmail(email: String) {
        getAppPreferences().edit().putString(UserPreferencesKey.USER_EMAIL,email).apply()
    }

    override fun setUserPassword(password: String) {
        getAppPreferences().edit().putString(UserPreferencesKey.PASSWORD,password).apply()
    }

    override fun setSignUpStatus(step: Int) {
        getAppPreferences().edit().putInt(AppEssentialKeys.SIGN_UP_STEP,step).apply()
    }

    override fun setUserLoginStatus(logged: Boolean) {
        getAppPreferences().edit().putBoolean(UserPreferencesKey.IS_LOGGED_IN,logged).apply()
    }

    override fun setWelcomeScreenStatus(shown: Boolean) {
        getAppPreferences().edit().putBoolean(AppEssentialKeys.WELCOME_SCREEN_SHOWED,shown).apply()
    }

    override fun logout() {
        preferences.apply {
            this.edit().clear()
            this.edit().commit()
        }
    }
    private fun getAppPreferences():SharedPreferences{
        return preferences
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
            const val USER_NAME = "user_name"
            const val USER_EMAIL = "user_email"
            const val PASSWORD = "password"
            const val IS_LOGGED_IN = "is_user_logged_in"
        }
        object AppEssentialKeys{
            const val WELCOME_SCREEN_SHOWED = "welcome_screen_showed"
            const val SIGN_UP_STEP = "sign_up_step"


        }

    }
}