package com.example.composesignup.feature.onboard.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesignup.core.di.AppDependencies
import com.example.composesignup.core.sessionManager.SessionManager
import com.example.composesignup.core.utils.TextFieldException
import com.example.composesignup.utlis.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val Tag = "LoginViewModel"
@HiltViewModel
class LoginViewModel @Inject constructor():ViewModel() {
    /**
     *1.
     * */
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    val action:(LoginUiAction)->Unit
    private var userEmail:String = ""
    private var userPassword:String = ""
    private var isUserSigned:Boolean = false

    init {
        action ={
            onUiAction(it)
        }
    }
    private fun onUiAction(action: LoginUiAction){
        when(action){
            is LoginUiAction.Login->{
                getSignupCredentials()
                //validateCredentials()
            }
            is LoginUiAction.Password->{
                password = action.password
            }
            is LoginUiAction.Email->{
                email = action.email
            }
            is LoginUiAction.ForgotPassword->{
                _uiState.update {
                    it.copy(
                        navToPasswordScreen = true
                    )
                }
            }
            is LoginUiAction.UiErrorShown->{
                _uiState.update {
                    it.copy(
                        exception = null,
                        uiText = null
                    )
                }
            }
            is LoginUiAction.ResetNavOptions->{
                resetNavOptions()
            }
        }
    }
    private fun validateCredentials(){
        viewModelScope.launch {
                val userCredentials = uiState.value.loginCredentials
                if (email == userCredentials?.email && password == userCredentials.password) {
                    _uiState.update {
                        it.copy(
                            isValid = true,
                            exception = TextFieldException(),
                            uiText = UiText.DynamicString("Successfull Login")
                        )
                    }
                    AppDependencies.persistentStore?.setUserLoginStatus(true)
                    Timber.tag(Tag).d("loginStatus...${AppDependencies.persistentStore?.isUserLoggedIn}")
                } else if (email!=userCredentials?.email || password!=userCredentials.password) {
                    _uiState.update {
                        it.copy(
                            isValid = false,
                            exception = TextFieldException(),
                            uiText = UiText.DynamicString("Invalid Credentials")
                        )
                    }
                }
        }
    }
    private fun resetNavOptions(){
        viewModelScope.launch (Dispatchers.IO){
            _uiState.update {
                it.copy(
                    navToPasswordScreen = false
                )
            }
        }
    }
    private fun getSignupCredentials(){
        viewModelScope.launch {
            val name = AppDependencies.persistentStore
                ?.name
            userEmail = AppDependencies.persistentStore?.email?:""
            userPassword = AppDependencies.persistentStore?.password?:""
            val isUserSignedIn = AppDependencies.persistentStore?.signUpStep
            val loginCredentials = LoginCredentials(userEmail,userPassword)
            Timber.tag(Tag).d("signupCredentials...$name,$userEmail,$userPassword")
            _uiState.update {
                it.copy(
                    loginCredentials = loginCredentials
                )
            }
            validateCredentials()
            if (isUserSignedIn==1)validateCredentials()
        }
    }
}
data class LoginUiState(
    val isValid:Boolean = false,
    val exception: Exception?=null,
    val uiText: UiText?=null,
    val navToPasswordScreen:Boolean = false,
    val loginCredentials: LoginCredentials?=null
)
sealed class LoginUiAction{
    data object Login:LoginUiAction()
    data object ForgotPassword:LoginUiAction()
    data object UiErrorShown:LoginUiAction()
    data object ResetNavOptions:LoginUiAction()
    data class Email(val email:String):LoginUiAction()
    data class Password(val password:String):LoginUiAction()
}
data class LoginCredentials(
    val email:String,
    val password:String
)