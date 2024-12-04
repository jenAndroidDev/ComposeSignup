package com.example.composesignup.feature.onboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val FORGOT_PASSWORD_ROUTE = "forgot_password_route"

fun NavController.navigateToForgotPassword(navOptions:NavOptions?=null) = navigate(FORGOT_PASSWORD_ROUTE)

fun NavGraphBuilder.forgotPasswordScreen(){
    composable(FORGOT_PASSWORD_ROUTE){
        ForgotPasswordRoute {

        }
    }
}