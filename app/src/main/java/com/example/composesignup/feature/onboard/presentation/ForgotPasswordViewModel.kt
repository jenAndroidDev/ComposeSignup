package com.example.composesignup.feature.onboard.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composesignup.core.sessionManager.SessionManager
import com.example.composesignup.core.utils.TextFieldException
import com.example.composesignup.utlis.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


private const val Tag = "ForgotPasswordViewModel"
@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val sessionManager: SessionManager
):ViewModel() {
    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState = _uiState.asStateFlow()

    val action:(ForgotPasswordUiAction)->Unit

    var email by mutableStateOf("")
            private set
    init {

        action ={
            onUiAction(it)
        }
    }
    private fun onUiAction(action: ForgotPasswordUiAction){
        when(action){
            is ForgotPasswordUiAction.Continue->{
                if (email.isEmpty()){
                    _uiState.update {
                        it.copy(
                            exception = TextFieldException(),
                            uiText = UiText.DynamicString("Please Enter Your Email To Continue")
                        )
                    }
                }else{
                    _uiState.update {
                        it.copy(
                            shouldNavToOtpScreen = true
                        )
                    }
                }
                Log.d(Tag, "onUiAction() called with: action = $action")
            }
            is ForgotPasswordUiAction.Cancel->{
                _uiState.update {
                    it.copy(
                        popBackStack = true
                    )
                }
            }
            is ForgotPasswordUiAction.Email->{
                email = action.email
            }
            is ForgotPasswordUiAction.UiErrorShown->{
                _uiState.update {
                    it.copy(
                        exception = null,
                        uiText = null
                    )
                }
            }
            is ForgotPasswordUiAction.ResetNavOptions->{
                resetNavOptions()
            }
        }
    }
    private fun resetNavOptions(){
        _uiState.update {
            it.copy(
                shouldNavToOtpScreen = false
            )
        }
    }
}
sealed class ForgotPasswordUiAction{
    data object Continue:ForgotPasswordUiAction()
    data object Cancel:ForgotPasswordUiAction()
    data class Email(val email:String):ForgotPasswordUiAction()
    data object UiErrorShown:ForgotPasswordUiAction()
    data object ResetNavOptions:ForgotPasswordUiAction()
}
data class ForgotPasswordUiState(
    val email: String="",
    val exception: Exception?=null,
    val uiText: UiText?=null,
    val shouldNavToOtpScreen:Boolean = false,
    val popBackStack:Boolean = false
)