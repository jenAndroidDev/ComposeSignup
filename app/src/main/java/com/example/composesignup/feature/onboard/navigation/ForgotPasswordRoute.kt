package com.example.composesignup.feature.onboard.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composesignup.feature.onboard.presentation.ForgotPasswordScreen


@Composable
fun ForgotPasswordRoute(
    modifier: Modifier= Modifier,
    onClick:()->Unit
){
    ForgotPasswordScreen(modifier = modifier)
}