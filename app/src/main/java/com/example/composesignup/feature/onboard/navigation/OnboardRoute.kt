package com.example.composesignup.feature.onboard.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composesignup.feature.onboard.presentation.OnboardScreen

@Composable
fun OnboardRoute(
    modifier: Modifier = Modifier,
    onClick:()->Unit
){
    Column {
        OnboardScreen(modifier = modifier,
            onForgotPasswordClick = onClick)
    }

}