package com.example.composesignup.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val SEARCH_ROUTE = "search_route"

fun NavController.navigateSearch(topLevelNavOptions: NavOptions) = navigate(SEARCH_ROUTE)

fun NavGraphBuilder.searchScreen(){
    composable(SEARCH_ROUTE){
        SearchRoute()
    }
}