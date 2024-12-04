package com.example.composesignup.feature.onboard.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.composesignup.core.sessionManager.SessionManager
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
    init {

        action ={
            onUiAction(it)
        }
    }
    private fun onUiAction(action: ForgotPasswordUiAction){
        when(action){
            is ForgotPasswordUiAction.Continue->{
                Log.d(Tag, "onUiAction() called with: action = $action")
            }
            is ForgotPasswordUiAction.Cancel->{

            }
            is ForgotPasswordUiAction.Email->{
                _uiState.update {
                    it.copy(
                        email = it.email
                    )
                }
            }
        }
    }

}
sealed class ForgotPasswordUiAction{
    data object Continue:ForgotPasswordUiAction()
    data object Cancel:ForgotPasswordUiAction()
    data class Email(val email:String):ForgotPasswordUiAction()
}
data class ForgotPasswordUiState(
    val email: String="",
    val exception: Exception?=null,
    val uiText: UiText?=null
)