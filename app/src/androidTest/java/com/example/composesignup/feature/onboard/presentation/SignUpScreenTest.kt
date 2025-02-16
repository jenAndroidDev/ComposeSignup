package com.example.composesignup.feature.onboard.presentation

import androidx.activity.compose.setContent
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composesignup.MainActivity
import com.example.composesignup.R
import com.example.composesignup.core.testing.util.TestTag
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpScreenTest{

    @get:Rule()
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
        composeTestRule
            .onNodeWithText(nameHint)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(emailHint)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(passwordHint)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(confirmPasswordHint)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(termsAndConditions)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(signUpButton)
            .assertIsDisplayed()

    }

    @Test
    fun signupScreen_verify_toggleButton_togglesCorrectly(){
        composeTestRule.activity.setContent {
            SignUpScreen()
        }
        composeTestRule
            .onNodeWithTag(TestTag.SWITCH_TEST_TAG)
            .performSemanticsAction(SemanticsActions.OnClick)
    }

    @Test
    fun signupScreen_verify_signupButton_worksCorrectly(){
        composeTestRule.activity.setContent {
            SignUpScreen()
        }
        composeTestRule
            .onNodeWithText(signUpButton)
            .performClick()
    }

    @Test
    fun testSuccessfulSignUp() {
        composeTestRule.activity.setContent {
           SignUpScreen()
        }

        composeTestRule.onNodeWithText(nameHint).performTextInput("John Doe")
        composeTestRule.onNodeWithText(emailHint).performTextInput("john@example.com")
        composeTestRule.onNodeWithText(passwordHint).performTextInput("Pass@1234")
        composeTestRule.onNodeWithText(confirmPasswordHint).performTextInput("Pass@1234")
        composeTestRule.onNodeWithTag(TestTag.SWITCH_TEST_TAG).performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.sign_up)).performClick()
    }

}