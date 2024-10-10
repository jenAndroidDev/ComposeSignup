package com.example.composesignup.core.sessionManager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class SessionManager(val context:Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("ComposeSignUp")

    companion object{
        val welcomeScreenShown = booleanPreferencesKey("welcome_screen")
    }
    suspend fun saveWelcomeScreenStatus(isShown:Boolean) = context.dataStore.edit {
        it[welcomeScreenShown] = isShown
    }
    suspend fun isWelcomeScreenShown() = context.dataStore.data.map {
        it[welcomeScreenShown]
    }

}