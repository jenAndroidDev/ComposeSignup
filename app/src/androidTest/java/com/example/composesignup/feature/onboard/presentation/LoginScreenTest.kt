package com.example.composesignup.feature.onboard.presentation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composesignup.MainActivity
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

    @Before
    fun setUp(){
//        composeTestRule.setContent {
//            val navController = rememberNavController()
//            ComposeSignupTheme {
//                NavHost(
//                    navController = navController,
//                    startDestination = ON
//                ) {
//                    composable(route = Screen.NotesScreen.route) {
//                        NotesScreen(navController = navController)
//                    }
//                }
//            }
//        }
    }

    @Test
    fun check_whether_loginButton_is_working(){
        composeTestRule.onNodeWithText("")
    }

    @Composable
    private fun LoginScreen(uiState: StateFlow<LoginUiState>){
        LoginScreen(uiState = uiState) {

        }
    }
    @Test
    fun loginScreen_displayedCorrectly() {
        composeTestRule.setContent {
            LoginScreen(onLoginSuccess = {})
        }

        // Verify email field is displayed
        composeTestRule.onNodeWithText("Email").assertExists()
        // Verify password field is displayed
        composeTestRule.onNodeWithText("Password").assertExists()
        // Verify login button is displayed
        composeTestRule.onNodeWithText("Login").assertExists()
        // Verify Forgot Password text is displayed
        composeTestRule.onNodeWithText("Forgot Password?").assertExists()
    }
}