package com.example.composesignup.feature.onboard.presentation


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composesignup.core.di.AppDependencies
import com.example.composesignup.core.sessionManager.SessionManager
import com.example.composesignup.core.utils.TextFieldException
import com.example.composesignup.feature.onboard.domain.usecase.InputFormUseCase
import com.example.composesignup.utlis.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val Tag = "SignUpViewModel"
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCase: InputFormUseCase
):ViewModel() {
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

    private var hasUserSignedIn:Boolean = false

    val action:(SignUpUiAction)->Unit
    init {
        action = {
            onUiAction(it)
        }
        /**
         * Check whether the user has already signed in or not. */

        Timber.tag(Tag).d("userSignedStatus...$hasUserSignedIn")
    }
    private fun onUiAction(action: SignUpUiAction){
        when(action){
            is SignUpUiAction.SignUp->{
                hasUserSignedIn = AppDependencies.persistentStore?.signUpStep==1
                if (!hasUserSignedIn) validateSignupForm() else _uiState.update {
                    it.copy(
                        exception = TextFieldException(),
                        uiText = UiText.DynamicString("You Have Already Signed In Please Log in to Continue")
                    )
                }
            }
            is SignUpUiAction.Email->{
                email = action.email
                Timber.tag(Tag).d("onUiAction() called with: email = %s", action.email)
            }
            is SignUpUiAction.UserName->{
                userName = action.name
                Timber.tag(Tag).d("onUiAction() called with: name = " + action.name)
            }
            is SignUpUiAction.Password->{
                password=action.password
                val passwordResult = useCase.passwordUseCase.invoke(password)
                    if (passwordResult.success){
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
                Timber.tag(Tag).d("onUiAction() called with: password = " + action.password)
            }
            is SignUpUiAction.ConfirmPassword->{
                confirmPassword = action.password
                confirmPassword()
                Timber.tag(Tag).d("onUiAction() called with: confirmPassword = " + action.password)
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
    private fun validateSignupForm(){
        val isPasswordConfirmed = uiState.value.isPasswordMatching
        val isTermsAccepted = uiState.value.isTermsAccepted
        val userEmail = email
        val userName = userName
        val password = password
        val emailValidation = useCase.userEmailUseCase.invoke(userEmail)
        val userNameValidation = useCase.userNameUseCase.invoke(userName)
        Timber.tag(Tag).d("formValidation...$userEmail,$userName,$password")

        if (isTermsAccepted && isPasswordConfirmed && emailValidation.success && userNameValidation.success ){
            AppDependencies.persistentStore?.run {
                setUserName(userName)
                setUserEmail(userEmail)
                setUserPassword(password)
                setSignUpStatus(1)
            }
            _uiState.update {
                it.copy(
                    isInputValid = true,
                    exception = TextFieldException(),
                    uiText = UiText.DynamicString(value = "Successfully Created Account.Please Login In.")
                )
            }
        }else if(emailValidation.success && userNameValidation.success
            && !isTermsAccepted && isPasswordConfirmed){
            _uiState.update {
                it.copy(
                    isInputValid = false,
                    exception = TextFieldException(),
                    uiText = UiText.DynamicString(value = "Please Accept Terms and Conditions")
                )
            }
        }else{
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
