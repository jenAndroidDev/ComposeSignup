package com.example.composesignup.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.composesignup.feature.detail.navigation.navigateDetails
import com.example.composesignup.feature.foryou.navigation.FOR_YOU_ROUTE
import com.example.composesignup.feature.foryou.navigation.navigateForYou
import com.example.composesignup.feature.onboard.navigation.navigateToForgotPassword
import com.example.composesignup.feature.onboard.navigation.navigateToOnboard
import com.example.composesignup.feature.otpverification.navigation.navigateToOtpVerificationScreen
import com.example.composesignup.feature.search.navigation.SEARCH_ROUTE
import com.example.composesignup.feature.search.navigation.navigateSearch
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberComposeSignUpState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
):ComposeSignUpState{

    return remember(
        coroutineScope,
        navController,
    ){
        ComposeSignUpState(
            navController,
            coroutineScope
        )
    }
}
@Stable
class ComposeSignUpState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope
) {
    val currentDestination:NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination:TopLevelDestinations?
        @Composable get() = when(currentDestination?.route){
            FOR_YOU_ROUTE->TopLevelDestinations.FOR_YOU
            SEARCH_ROUTE->TopLevelDestinations.SEARCH
            else->null
        }

    val topLevelDestinations:List<TopLevelDestinations> = TopLevelDestinations.entries

    fun navigateToTopLevelDestination(topLevelDestinations: TopLevelDestinations){

        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            popUpToTop(navController)
            launchSingleTop = true
            restoreState = true
        }
        when(topLevelDestinations){
            TopLevelDestinations.FOR_YOU->{
                navController.navigateForYou(topLevelNavOptions)
            }
            TopLevelDestinations.SEARCH->{
                navController.navigateSearch(topLevelNavOptions)
            }
        }
    }

    fun navigateToDetailScreen() = navController.navigateDetails()

    fun navigateToForgotPasswordScreen() = navController.navigateToForgotPassword()

    fun navigateToOtpVerificationScreen() = navController.navigateToOtpVerificationScreen()



}

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
    }
}