package com.example.composesignup.core.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.example.composesignup.R
import com.example.composesignup.core.designsystem.icon.ComposeSignUpIcons
import com.example.composesignup.feature.foryou.navigation.FOR_YOU_ROUTE
import com.example.composesignup.feature.search.navigation.SEARCH_ROUTE

enum class TopLevelDestinations(
    val route:String,
    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
) {
    FOR_YOU(
        title = "For You",
        selectedIcon = ComposeSignUpIcons.HomeSelected,
        unselectedIcon = ComposeSignUpIcons.HomeUnSelected,
        route = FOR_YOU_ROUTE
        ),
    SEARCH(
        title = "Search",
        selectedIcon = ComposeSignUpIcons.AccountSelected,
        unselectedIcon = ComposeSignUpIcons.AccountUnSelected,
        route = SEARCH_ROUTE
    )
}