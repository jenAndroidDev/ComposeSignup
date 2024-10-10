package com.example.composesignup.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.composesignup.ComposeSignUp
import com.example.composesignup.feature.detail.navigation.DETAIL_ROUTE
import com.example.composesignup.feature.detail.navigation.detailScreen
import com.example.composesignup.feature.foryou.navigation.FOR_YOU_ROUTE
import com.example.composesignup.feature.foryou.navigation.forYouScreen
import com.example.composesignup.feature.search.navigation.SEARCH_ROUTE
import com.example.composesignup.feature.search.navigation.searchScreen
import com.example.composesignup.feature.welcome.navigation.welcomeScreen

/*
* All */
@Composable
fun ComposeSignUpNavHost(
    modifier: Modifier,
    appState: ComposeSignUpState,
    startDestination:String

){
    val navController = appState.navController

    NavHost(navController = navController,
        startDestination = startDestination,
        modifier = modifier){
        welcomeScreen{
            appState.navigateToTopLevelDestination(TopLevelDestinations.FOR_YOU)
        }
        forYouScreen{
            appState.navigateToDetailScreen()
        }
        searchScreen()
        detailScreen()
    }

}