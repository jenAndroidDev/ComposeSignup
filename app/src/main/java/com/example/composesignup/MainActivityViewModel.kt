package com.example.composesignup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesignup.core.sessionManager.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val sessionManager: SessionManager
):ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityUiState())
    val uiState = _uiState.asStateFlow()

    init {
        isWelcomeScreenShown()
    }
    private fun isWelcomeScreenShown(){
        viewModelScope.launch(Dispatchers.IO) {
            val isWelcomeScreenShown = sessionManager.isWelcomeScreenShown().firstOrNull()?:false
            if (isWelcomeScreenShown){
                _uiState.update {
                    it.copy(
                        isWelcomeScreenShown = true
                    )
                }
            }
        }
    }

}
data class MainActivityUiState(
    val isUserLoggedIn:Boolean  = false,
    val isWelcomeScreenShown:Boolean = false
)