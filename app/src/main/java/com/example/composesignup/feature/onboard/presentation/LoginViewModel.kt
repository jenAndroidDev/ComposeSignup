package com.example.composesignup.feature.onboard.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesignup.utlis.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val Tag = "LoginViewModel"
class LoginViewModel:ViewModel() {

    /**
     * */
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    val action:(LoginUiAction)->Unit

    init {

        action ={
            onUiAction(it)
        }
    }
    private fun onUiAction(action: LoginUiAction){
        when(action){
            is LoginUiAction.Login->{

            }
            is LoginUiAction.Password->{
                password = action.password
            }
            is LoginUiAction.Email->{
                email = action.email
            }
            is LoginUiAction.ForgotPassword->{

            }
        }
    }

}
data class LoginUiState(
    val isValid:Boolean = false,
    val exception: Exception?=null,
    val uiText: UiText?=null
)
sealed class LoginUiAction{
    data object Login:LoginUiAction()
    data object ForgotPassword:LoginUiAction()
    data class Email(val email:String):LoginUiAction()
    data class Password(val password:String):LoginUiAction()
}