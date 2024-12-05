package com.example.composesignup.feature.otpverification.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composesignup.feature.otpverification.presentation.OtpVerificationScreen

@Composable
fun OtpVerificationRoute(){
    Column {
        OtpVerificationScreen(modifier = Modifier)
    }
}