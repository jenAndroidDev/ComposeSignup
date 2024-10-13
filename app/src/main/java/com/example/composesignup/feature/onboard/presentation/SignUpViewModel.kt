package com.example.composesignup.feature.onboard.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val Tag = "SignUpViewModel"
class SignUpViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()
    /**
     * Storing User Inputs in Ui State is Avoided.
     */
    var userName by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set

    val action:(SignUpUiAction)->Unit
    init {
        action = {
            onUiAction(it)
        }
    }
    private fun onUiAction(action: SignUpUiAction){
        when(action){
            is SignUpUiAction.SignUp->{

            }
            is SignUpUiAction.Email->{
                email = action.email
                Log.d(Tag, "onUiAction() called with: email = ${action.email}")
            }
            is SignUpUiAction.UserName->{
                userName = action.name
                Log.d(Tag, "onUiAction() called with: name = ${action.name}")
            }
            is SignUpUiAction.Password->{
                password=action.password
                Log.d(Tag, "onUiAction() called with: password = ${action.password}")
            }
            is SignUpUiAction.ConfirmPassword->{
                confirmPassword = action.password
                Log.d(Tag, "onUiAction() called with: confirmPassword = ${action.password}")
            }
        }
    }
    private fun validateUserInput(){
        viewModelScope.launch (Dispatchers.IO){
            //TODO validate input and redirect to next screen.
        }
    }

}
data class SignUpUiState(
    val isCredentialsValid:Boolean = false
)
sealed class SignUpUiAction{
    data object SignUp:SignUpUiAction()
    data class UserName(val name:String):SignUpUiAction()
    data class Email(val email:String):SignUpUiAction()
    data class Password(val password:String):SignUpUiAction()
    data class ConfirmPassword(val password:String):SignUpUiAction()
}