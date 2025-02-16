package com.example.composesignup.feature.onboard.presentation

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.composesignup.MainActivity
import com.example.composesignup.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest{

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var nameHint:String
    private lateinit var emailHint:String
    private lateinit var passwordHint:String
    private lateinit var confirmPasswordHint:String
    private lateinit var termsAndConditions:String
    private lateinit var signUpButton:String

    @Before
    fun setUp(){
        composeTestRule.activity.apply {
            nameHint =getString(R.string.name_hint)
            emailHint = getString(R.string.email_hint)
            passwordHint = getString(R.string.password_hint)
            confirmPasswordHint = getString(R.string.password_confirm_hint)
            termsAndConditions = getString(R.string.terms_and_conditions)
            signUpButton = getString(R.string.sign_up)
        }
    }

    @Test
    fun signupScreen_displaysCorrectly(){
        composeTestRule.activity.setContent {
            SignUpScreen()
        }
        composeTestRule.onNodeWithText(nameHint).assertIsDisplayed()
        composeTestRule.onNodeWithText(emailHint).assertIsDisplayed()
        composeTestRule.onNodeWithText(passwordHint).assertIsDisplayed()
        composeTestRule.onNodeWithText(confirmPasswordHint).assertIsDisplayed()
        composeTestRule.onNodeWithText(termsAndConditions).assertIsDisplayed()
        composeTestRule.onNodeWithText(signUpButton).assertIsDisplayed()

    }

}