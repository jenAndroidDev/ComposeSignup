package com.example.composesignup.feature.onboard.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composesignup.core.utils.TextFieldException
import com.example.composesignup.utlis.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val Tag = "SignUpViewModel"
class SignUpViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()
    /**
     * Storing User Inputs in Ui State is Avoided as
     * reactive streams causes delays in updating the TextField.
     *
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
                validateUserInput()
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
                    if (isPasswordValid(password)){
                        _uiState.update {
                            it.copy(
                                isPasswordSizeValid = true,
                            )
                        }
                    }else{
                        _uiState.update {
                            it.copy(
                                isPasswordSizeValid = false,
                            )
                        }
                    }
                Log.d(Tag, "onUiAction() called with: password = ${action.password}")
            }
            is SignUpUiAction.ConfirmPassword->{
                confirmPassword = action.password
                confirmPassword()
                Log.d(Tag, "onUiAction() called with: confirmPassword = ${action.password}")
            }
            is SignUpUiAction.ToggleTermsAndCondition->{
                val isTermsAccepted = uiState.value.isTermsAccepted
                _uiState.update {
                    it.copy(
                        isTermsAccepted = !isTermsAccepted
                    )
                }
            }
            is SignUpUiAction.UiErrorShown->{
                uiErrorShown()
            }
        }
    }

    private fun isPasswordValid(password:String):Boolean{
        val pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$".toRegex()
        return password.matches(pattern)
    }

    private fun confirmPassword(){
        val password = this.password
        val confirmPassword = this.confirmPassword
        if (password==confirmPassword){
            _uiState.update {
                it.copy(
                    isPasswordMatching = true
                )
            }
        }else{
            _uiState.update {
                it.copy(
                    isPasswordMatching = false
                )
            }
        }
    }
    private fun validateUserInput(){
        val isPasswordConfirmed = uiState.value.isPasswordMatching
        val isTermsAccepted = uiState.value.isTermsAccepted
        if (userName.isNotEmpty() && isPasswordConfirmed && email.isNotEmpty()
            && isTermsAccepted){
            _uiState.update {
                it.copy(
                    isInputValid = true
                )
            }
        }else if (userName.isNotEmpty() && isPasswordConfirmed && email.isNotEmpty()
            && !isTermsAccepted ){
            _uiState.update {
                it.copy(
                    isInputValid = false,
                    exception = TextFieldException(),
                    uiText = UiText.DynamicString("Please Accept Terms and Condition")

                )
            }
        } else{
            _uiState.update {
                it.copy(
                    isInputValid = false,
                    exception = TextFieldException(),
                    uiText = UiText.DynamicString("Please Enter All the required fields")
                )
            }
        }
    }
    private fun uiErrorShown(){
        _uiState.update {
            it.copy(
                exception = null,
                uiText = null
            )
        }
    }

}
data class SignUpUiState(
    val isCredentialsValid:Boolean = false,
    val isPasswordMatching:Boolean = false,
    val isPasswordSizeValid:Boolean = false,
    val isTermsAccepted:Boolean = false,
    val isInputValid:Boolean  = false,
    val uiText: UiText?=null,
    val exception:Exception?=null
)
sealed class SignUpUiAction{
    data object SignUp:SignUpUiAction()
    data object ToggleTermsAndCondition:SignUpUiAction()
    data class UserName(val name:String):SignUpUiAction()
    data class Email(val email:String):SignUpUiAction()
    data class Password(val password:String):SignUpUiAction()
    data class ConfirmPassword(val password:String):SignUpUiAction()
    data object UiErrorShown:SignUpUiAction()
}
