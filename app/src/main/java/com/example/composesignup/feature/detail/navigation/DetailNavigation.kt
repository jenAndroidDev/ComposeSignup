package com.example.composesignup.feature.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val DETAIL_ROUTE = "detail_route"

fun NavController.navigateDetails(navOptions: NavOptions? = null) = navigate(DETAIL_ROUTE)

fun NavGraphBuilder.detailScreen(){
    composable(DETAIL_ROUTE){
        DetailRoute()
    }
}