package com.example.composesignup.feature.onboard.presentation

import androidx.lifecycle.ViewModel
import com.example.composesignup.utlis.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val Tag = "LoginViewModel"
class LoginViewModel:ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    val action:(LoginUiAction)->Unit

    init {

        action ={
            onUiAction()
        }
    }
    private fun onUiAction(){

    }

}
data class LoginUiState(
    val isValid:Boolean = false,
    val exception: Exception?=null,
    val uiText: UiText?=null
)
sealed class LoginUiAction{
    data object Login:LoginUiAction()
}