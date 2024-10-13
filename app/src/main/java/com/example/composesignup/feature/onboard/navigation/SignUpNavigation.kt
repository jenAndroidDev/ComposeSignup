package com.example.composesignup.feature.onboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val SIGNUP_ROUTE = "sign_up_route"

fun NavController.navigateToSignUp(navOptions: NavOptions) = navigate(SIGNUP_ROUTE)

fun NavGraphBuilder.signUpScreen(onClick:()->Unit){
    composable(SIGNUP_ROUTE){
        SignupRoute()
    }
}
