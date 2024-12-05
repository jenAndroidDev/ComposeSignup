package com.example.composesignup.feature.otpverification.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val OTP_VERIFICATION_ROUTE = "otp_verification_route"

fun NavController.navigateToOtpVerificationScreen(navOptions: NavOptions?=null) = navigate(
    OTP_VERIFICATION_ROUTE)

fun NavGraphBuilder.otpVerificationScreen(
    onClick:()->Unit
){
    composable(route = OTP_VERIFICATION_ROUTE){
        OtpVerificationRoute()
    }
}