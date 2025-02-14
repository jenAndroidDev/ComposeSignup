package com.example.composesignup.feature.onboard.presentation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.core.content.ContextCompat.getString
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composesignup.MainActivity
import com.example.composesignup.R
import com.example.composesignup.feature.onboard.navigation.FORGOT_PASSWORD_ROUTE
import com.example.composesignup.ui.theme.ComposeSignupTheme
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ForgotPasswordScreenTest{

    @get:Rule()
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun forgotPasswordScreen_displayedCorrectly()  {
        composeTestRule.activity.setContent {
            ForgotPasswordScreen()
        }
        composeTestRule.onNodeWithText("Forgot Password").isDisplayed()
    }
}