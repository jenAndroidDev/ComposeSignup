package com.example.composesignup

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.composesignup.components.ComposeSignUpBarItem
import com.example.composesignup.components.ComposeSignUpNavigationBar
import com.example.composesignup.core.navigation.ComposeSignUpNavHost
import com.example.composesignup.core.navigation.ComposeSignUpState
import com.example.composesignup.core.navigation.TopLevelDestinations

@Composable
fun ComposeSignUpApp(
    appState:ComposeSignUpState,
    modifier: Modifier = Modifier
    ){
    ComposeSignUp(
        appState = appState,
        modifier = modifier
    )
}

@Composable
internal fun ComposeSignUp(
    appState: ComposeSignUpState,
    modifier: Modifier,
    variance: String = "Default",
) {
    Scaffold(bottomBar = {
        ComposeSignUpBottomBar(
            destinations = appState.topLevelDestinations,
            currentDestination = appState.currentDestination,
            modifier = modifier,
            onNavigateToDestination = appState::navigateToTopLevelDestination
        )
    }) {
        Log.d("scaffold", "ComposeSignUpApp() called...$it")
        Column(modifier = modifier.fillMaxSize()) {
            ComposeSignUpNavHost(
                modifier = modifier,
                appState = appState)
        }
    }

}

@Composable
private fun ComposeSignUpBottomBar(
    destinations: List<TopLevelDestinations>,
    currentDestination:NavDestination?,
    onNavigateToDestination:(TopLevelDestinations)->Unit,
    modifier: Modifier
){
    ComposeSignUpNavigationBar(modifier = modifier.fillMaxWidth()) {
        destinations.forEach {destination->
            Log.d("ComposeSignUpNavigation", "ComposeSignUpBottomBar() called with: destination = $destination,${currentDestination?.route}")
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            Log.d("ComposeSignUpNavigation", "ComposeSignUpBottomBar() called with: destination = $selected")

            ComposeSignUpBarItem(
                selected = selected,
                onClick = {
                    onNavigateToDestination.invoke(destination)
                },
                icon = { Icons.Default.AccountCircle },
                label = {
                    destination.title
                })
        }
    }

}
private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestinations) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
