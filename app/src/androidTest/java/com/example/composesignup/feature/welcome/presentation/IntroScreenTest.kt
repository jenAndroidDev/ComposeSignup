package com.example.composesignup.feature.welcome.presentation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.composesignup.MainActivity
import org.junit.Rule

class IntroScreenTest{

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    lateinit var skipButton:String
}