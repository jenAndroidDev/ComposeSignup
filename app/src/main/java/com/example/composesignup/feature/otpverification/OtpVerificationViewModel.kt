package com.example.composesignup.feature.otpverification

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val Tag = "OtpVerificationViewModel"
class OtpVerificationViewModel:ViewModel() {

    private val _uiState = MutableStateFlow(OtpVerificationUiState())
    val uiState = _uiState.asStateFlow()

    val action:(OtpVerificationUiAction)->Unit

    init {

        action = {
            onUiAction(it)
        }
    }
    private fun onUiAction(action: OtpVerificationUiAction){
        when(action){

            is OtpVerificationUiAction.VerifyOtp->{

            }
        }
    }

    fun saveEnteredOtp(otp: String) {
        _uiState.update {
            it.copy(
                otp = otp,
                enableButton = true
            )
        }
    }
}
sealed interface OtpVerificationUiAction{
    data class VerifyOtp(val otp:String):OtpVerificationUiAction
}
data class OtpVerificationUiState(
    val otp:String = "",
    val msg:String = "",
    val enableButton:Boolean = false
)