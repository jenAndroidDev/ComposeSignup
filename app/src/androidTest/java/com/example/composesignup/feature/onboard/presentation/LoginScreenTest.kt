package com.example.composesignup.feature.onboard.presentation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composesignup.MainActivity
import com.example.composesignup.R
import com.example.composesignup.core.navigation.TopLevelDestinations
import com.example.composesignup.ui.theme.ComposeSignupTheme
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.StateFlow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


class LoginScreenTest{

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var emailHint:String
    private lateinit var passwordHint:String

    @Before
    fun setUp(){
        composeTestRule.activity.apply {
            emailHint = getString(R.string.email_hint)
            passwordHint = getString(R.string.password_hint)
        }
    }

    @Test
    fun loginScreen_displayedCorrectly() {
        composeTestRule.activity.setContent {
            LoginScreen(onLoginSuccess = {})
        }
        composeTestRule.onNodeWithText(emailHint).assertExists()
        composeTestRule.onNodeWithText(passwordHint).assertExists()
        composeTestRule.onNodeWithText("Login").assertExists()
        composeTestRule.onNodeWithText("Forgot Password?").assertExists()
    }

    @Test
    fun loginScreen_emailTextField_performsCorrectly(){
        composeTestRule.activity.setContent {
            LoginScreen(onLoginSuccess = {})
        }
        val testEmail = "jenin@gmail.com"
        composeTestRule.onNodeWithText(emailHint).performTextInput(testEmail)
        composeTestRule.onNodeWithText(testEmail).assertTextContains(testEmail)

//        composeTestRule.onNodeWithText(passwordHint).performTextInput(testPassword)
//        composeTestRule.onNodeWithText(passwordHint).assertTextContains(testPassword)

    }

    @Test
    fun loginScreen_passwordTextField_performsCorrectly(){
        composeTestRule.activity.setContent {
            LoginScreen {

            }
        }
        val testPassword = "adamo"
        composeTestRule.onNodeWithText(passwordHint).performTextInput(testPassword)
    }
}