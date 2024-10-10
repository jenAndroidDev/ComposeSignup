package com.example.composesignup.feature.welcome.navigation

import androidx.compose.runtime.Composable
import com.example.composesignup.feature.welcome.presentation.IntroScreen

@Composable
fun WelcomeRoute(onClick:()->Unit){
    IntroScreen(onClick = onClick)
}