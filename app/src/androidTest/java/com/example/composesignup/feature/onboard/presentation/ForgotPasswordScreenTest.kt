package com.example.composesignup.feature.onboard.presentation


import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.core.content.ContextCompat
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



    @Before
    fun setUp(){
        val context = composeTestRule.activity
        composeTestRule.activity.apply {
        forgotPasswordTitle = getString(context,R.string.forgot_password_title)
        cancelButton = getString(context,R.string.btn_cancel)
        continueButton = getString(context,R.string.btn_continue)
        forgotPasswordImageDesc = getString(context,R.string.forgot_password_image_description)
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

    }


}