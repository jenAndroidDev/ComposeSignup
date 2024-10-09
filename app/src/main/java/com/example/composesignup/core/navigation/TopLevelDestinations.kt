package com.example.composesignup.core.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.example.composesignup.R
import com.example.composesignup.core.designsystem.icon.ComposeSignUpIcons

enum class TopLevelDestinations(
    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
) {
    FOR_YOU(
        title = "For You",
        selectedIcon = ComposeSignUpIcons.HomeSelected,
        unselectedIcon = ComposeSignUpIcons.HomeUnSelected
        ),
    SEARCH(
        title = "Accounts",
        selectedIcon = ComposeSignUpIcons.AccountSelected,
        unselectedIcon = ComposeSignUpIcons.AccountUnSelected
    )
}