package com.example.composesignup.core.sessionManager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map


class SessionManager(private val context:Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("ComposeSignUp")
    companion object{
        val welcomeScreenShown = booleanPreferencesKey("welcome_screen")
        val LOGIN = booleanPreferencesKey("login")
        val SIGNUP = intPreferencesKey("signup")
        val EMAIL = stringSetPreferencesKey("email")
        val PASSWORD  = stringSetPreferencesKey("password")
        val NAME = stringSetPreferencesKey("username")
    }
    suspend fun saveWelcomeScreenStatus(isShown:Boolean) = context.dataStore.edit {
        it[welcomeScreenShown] = isShown
    }
    suspend fun isWelcomeScreenShown() = context.dataStore.data.map {
        it[welcomeScreenShown]
    }
    suspend fun setUserName(name:String){
        context.dataStore.edit {
            it[NAME] = setOf(name)
        }
    }
    suspend fun getUserName() = context.dataStore.data.map {
        it[NAME]
    }
    suspend fun setUserEmail(email:String){
        context.dataStore.edit {
            it[EMAIL] = setOf(email)
        }
    }
    suspend fun getUserEmail() = context.dataStore.data.map {
        it[EMAIL]
    }
    suspend fun setUserPassword(password:String){
        context.dataStore.edit {
            it[PASSWORD] = setOf(password)
        }
    }
    suspend fun getUserPassword() = context.dataStore.data.map {
        it[PASSWORD]
    }
    suspend fun setUserLoginStatus(loginStatus:Boolean) = context.dataStore.edit {
        it[LOGIN] = loginStatus
    }
    fun getUserLoginStatus() =context.dataStore.data.map {
        it[LOGIN]
    }
    suspend fun setSignUpStatus(signupStatus:Int) = context.dataStore.edit {
        it[SIGNUP] = signupStatus
    }
    fun getSignupStatus() = context.dataStore.data.map {
        it[SIGNUP]?:0
    }
}