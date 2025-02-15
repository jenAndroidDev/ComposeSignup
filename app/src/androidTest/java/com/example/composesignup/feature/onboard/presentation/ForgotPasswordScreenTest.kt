package com.example.composesignup.feature.onboard.presentation


import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.core.content.ContextCompat.getString
import com.example.composesignup.MainActivity
import com.example.composesignup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ForgotPasswordScreenTest{

    @get:Rule()
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private lateinit var forgotPasswordTitle:String
    private lateinit var cancelButton:String
    private lateinit var continueButton:String
    private lateinit var forgotPasswordImageDesc:String
    private lateinit var emailHint:String



    @Before
    fun setUp(){
        val context = composeTestRule.activity
        composeTestRule.activity.apply {
        forgotPasswordTitle = getString(context,R.string.forgot_password_title)
        cancelButton = getString(context,R.string.btn_cancel)
        continueButton = getString(context,R.string.btn_continue)
        forgotPasswordImageDesc = getString(context,R.string.forgot_password_image_description)
        emailHint = getString(context,R.string.email_hint)
        }

    }

    @Test
    fun forgotPasswordScreen_displayedCorrectly()  {
        composeTestRule.activity.setContent {
            ForgotPasswordScreen()
        }
        composeTestRule.onNodeWithText(forgotPasswordTitle).assertIsDisplayed()
        composeTestRule.onNodeWithText(cancelButton).assertIsDisplayed()
        composeTestRule.onNodeWithText(continueButton).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(forgotPasswordImageDesc).assertIsDisplayed()
        composeTestRule.onNodeWithText(emailHint).assertIsDisplayed()

    }

    @Test
    fun forgotPasswordScreen_buttonsWorkingCorrectly(){
        composeTestRule.activity.setContent {
            ForgotPasswordScreen()
        }
        composeTestRule.onNodeWithText(cancelButton).assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText(continueButton).assertIsDisplayed().performClick()
    }

    @Test
    fun forgotPasswordScreen_enterEmail_toResetPassword(){
        composeTestRule.activity.setContent {
            ForgotPasswordScreen()
        }
        composeTestRule.onNodeWithText(emailHint).performTextInput("rjjenin@gmail.com")
    }


}