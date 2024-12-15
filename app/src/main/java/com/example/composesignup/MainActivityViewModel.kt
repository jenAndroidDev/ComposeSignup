package com.example.composesignup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesignup.core.sessionManager.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.sign

private const val Tag = "MainActivityViewModel"
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val sessionManager: SessionManager
):ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityUiState())
    val uiState = _uiState.asStateFlow()

    init {

        println("isWelcomeScreen")
    }
    fun isWelcomeScreenShown(){
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                sessionManager.getSignupStatus(),
                sessionManager.isWelcomeScreenShown(),
                ::Pair
            ).collectLatest{
                (signup,welcomeScreen)->
                Timber.tag(Tag).d("${signup},${welcomeScreen}")
                if (signup==0 && welcomeScreen) {
                    Timber.tag(Tag).d("$signup,$welcomeScreen")
                    _uiState.update {
                        it.copy(
                            isUserLoggedIn = false,
                            isWelcomeScreenShown = true
                        )
                    }
                }else if (signup==1 && welcomeScreen){
                    _uiState.update {
                        it.copy(
                            isUserLoggedIn = true,
                            isWelcomeScreenShown = true
                        )
                    }
                }else{
                    _uiState.update {
                        it.copy(
                            isUserLoggedIn = false,
                            isWelcomeScreenShown = false
                        )
                    }
                }
            }
        }
    }
}
data class MainActivityUiState(
    val isUserLoggedIn:Boolean  = false,
    val isWelcomeScreenShown:Boolean = false
)