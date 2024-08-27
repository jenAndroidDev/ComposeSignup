package com.example.composesignup.feature.foryou.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.composesignup.feature.detail.navigation.detailScreen

const val FOR_YOU_ROUTE = "for_you_route"

fun NavController.navigateForYou(topLevelNavOptions: NavOptions) = navigate(FOR_YOU_ROUTE)

fun NavGraphBuilder.forYouScreen(onClick:()->Unit){
    composable(FOR_YOU_ROUTE){
        ForYouRoute(
            onClick = onClick
        )
    }
}