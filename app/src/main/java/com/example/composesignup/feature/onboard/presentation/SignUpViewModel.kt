package com.example.composesignup.feature.onboard.presentation

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
}