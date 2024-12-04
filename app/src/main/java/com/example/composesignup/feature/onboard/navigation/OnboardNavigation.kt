package com.example.composesignup.feature.onboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.composesignup.feature.onboard.presentation.OnboardScreen

const val ONBOARD_ROUTE = "onboard_route"

fun NavController.navigateToOnboard(navOptions: NavOptions?=null) = navigate(ONBOARD_ROUTE)

fun NavGraphBuilder.onboardScreen(onForgotPasswordClick:()->Unit){
    composable(ONBOARD_ROUTE){
        OnboardRoute(onClick = onForgotPasswordClick)
    }
}