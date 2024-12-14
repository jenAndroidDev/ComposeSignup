package com.example.composesignup.feature.onboard.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import timber.log.Timber


const val ONBOARD_ROUTE = "onboard_route"

fun NavController.navigateToOnboard(navOptions: NavOptions?=null) = navigate(ONBOARD_ROUTE)

fun NavGraphBuilder.onboardScreen(
    onForgotPasswordClick:()->Unit,
    onLoginSuccess:()->Unit
    ){
    composable(ONBOARD_ROUTE){
        Timber.tag("Route").d("onboardScreen() called")
        OnboardRoute(onClick = onForgotPasswordClick,
           onLoginSuccess = onLoginSuccess
        )
    }
}