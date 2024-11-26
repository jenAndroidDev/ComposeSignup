package com.example.composesignup.feature.onboard.presentation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel

private const val Tag  = "OnboardScreenViewModel"
class OnboardScreenViewModel:ViewModel() {

}
data class OnboardTabItem(
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val description:String
)