package com.example.composesignup.core.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.composesignup.feature.detail.navigation.detailScreen
import com.example.composesignup.feature.foryou.navigation.forYouScreen
import com.example.composesignup.feature.onboard.navigation.forgotPasswordScreen
import com.example.composesignup.feature.onboard.navigation.navigateToOnboard
import com.example.composesignup.feature.onboard.navigation.onboardScreen
import com.example.composesignup.feature.otpverification.navigation.otpVerificationScreen
import com.example.composesignup.feature.search.navigation.searchScreen
import com.example.composesignup.feature.welcome.navigation.welcomeScreen

/*
* All */
private const val Tag = "ComposeSignupNavHost"
@Composable
fun ComposeSignUpNavHost(
    modifier: Modifier,
    appState: ComposeSignUpState,
    startDestination:String,

){
    val navController = appState.navController

    NavHost(navController = navController,
        startDestination = startDestination,
        modifier = modifier){
        welcomeScreen{
            appState.navController.navigateToOnboard()
        }
        forYouScreen(appState::navigateToDetailScreen)
        searchScreen()
        detailScreen()
        onboardScreen({
            appState.navigateToForgotPasswordScreen()
        },{
            appState.navigateToTopLevelDestination(TopLevelDestinations.FOR_YOU)
        })
        forgotPasswordScreen({
            appState.navigateToOtpVerificationScreen()
        },{
            appState.navController.popBackStack()
        })
        otpVerificationScreen{

        }
    }

}