package com.example.composesignup.feature.welcome.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.composesignup.feature.welcome.presentation.IntroScreen

const val WELCOME_ROUTE = "welcome_route"

fun NavController.navigateToWelcome() = navigate(WELCOME_ROUTE)

fun NavGraphBuilder.welcomeScreen(onClick:()->Unit){
    composable(WELCOME_ROUTE){
        WelcomeRoute(onClick = onClick)
    }
}