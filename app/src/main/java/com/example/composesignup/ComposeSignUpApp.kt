package com.example.composesignup

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.composesignup.components.ComposeSignUpBarItem
import com.example.composesignup.components.ComposeSignUpNavigationBar
import com.example.composesignup.core.navigation.ComposeSignUpNavHost
import com.example.composesignup.core.navigation.ComposeSignUpState
import com.example.composesignup.core.navigation.TopLevelDestinations

private const val Tag = "ComposeSignUpApp"
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
            onNavigateToDestination = appState::navigateToTopLevelDestination,
            appState = appState
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
    modifier: Modifier,
    destinations: List<TopLevelDestinations>,
    appState: ComposeSignUpState,
    currentDestination:NavDestination?,
    onNavigateToDestination:(TopLevelDestinations)->Unit,
){
    val isTopLevelDestination = TopLevelDestinations.entries.any {
        it.route==currentDestination?.route
    }
    if (isTopLevelDestination) {
        ComposeSignUpNavigationBar(modifier = modifier.fillMaxWidth()) {
            destinations.onEachIndexed { index, topLevelDestination ->
                Log.d(
                    Tag,
                    "ComposeSignUpBottomBar() called with: index = $index, topLevelDestination = $topLevelDestination, route = ${currentDestination?.route}",
                )
                ComposeSignUpBarItem(
                    selected = (appState.currentDestination?.route == topLevelDestination.route),
                    icon = {
                        Icon(
                            imageVector = destinations[index].selectedIcon,
                            contentDescription = destinations[index].title
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = destinations[index].unselectedIcon,
                            contentDescription = topLevelDestination.title
                        )
                    },
                    label = {
                        Text(topLevelDestination.title)
                    },
                    onClick = {
                        onNavigateToDestination.invoke(topLevelDestination)
                    }
                )
            }
        }
    }
}
private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestinations) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
