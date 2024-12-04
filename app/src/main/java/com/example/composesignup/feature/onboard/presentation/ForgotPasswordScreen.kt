package com.example.composesignup.feature.onboard.presentation

import android.preference.PreferenceActivity.Header
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composesignup.R
import com.example.composesignup.core.designsystem.components.AppHeader
import com.example.composesignup.core.designsystem.components.ComposeSignUpButton
import com.example.composesignup.core.designsystem.components.ComposeSignUpTextField
import com.example.composesignup.core.designsystem.icon.ComposeSignUpIcons
import com.example.composesignup.ui.theme.GREY20
import com.example.composesignup.ui.theme.Green80
import com.example.composesignup.ui.theme.fontFamily

@Composable
fun ForgotPasswordScreen(modifier: Modifier){

    Column(modifier = modifier.fillMaxSize()
        .background(color = Color.White)
        ) {
        Header(modifier = modifier)
        ForgotPasswordImage(modifier = modifier)
        Spacer(modifier = modifier.height(12.dp))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = stringResource(id = R.string.email_hint),
            value = "",
            leadingIcon = {
                Icon(imageVector = ComposeSignUpIcons.Email, contentDescription = null)
            }
        ) {
        }
        Spacer(modifier = modifier.weight(1f))
        ComposeSignUpButton(
            modifier = modifier,
            text = "Continue",
            textColor = Green80

        ) {
            //TODO==>onClick on Continue
        }
        Spacer(modifier = modifier.height(12.dp))
        ComposeSignUpButton (
            modifier = modifier,
            backgroundColor = GREY20,
            text = "Cancel",
            borderColor = Color.Black.copy(alpha = 0.1f),
            textColor = Color.Black
        ){
            //TODO==>onClick on Cancel
        }
        Spacer(modifier = modifier.weight(1f))

    }
}

@Composable
private fun Header(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(24.dp))
        AppHeader(modifier = modifier.padding(end = 8.dp))
        Spacer(modifier = modifier.height(18.dp))
        Text(
            text = stringResource(R.string.welcome_text),
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = stringResource(R.string.onboard_hint),
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            style = MaterialTheme.typography.labelMedium,
            color = Color.DarkGray,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(20.dp))
    }
}
private val RoundedCornerRadius = 12.dp

@Composable
private fun ForgotPasswordImage(
    modifier: Modifier
){
    Image(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            painter = painterResource(id = R.drawable.welcome_image4),
            contentDescription = "intro_item",
            contentScale = ContentScale.Fit
        )
}

@Preview
@Composable
private fun ForgotPasswordPreview(){
    ForgotPasswordScreen(modifier = Modifier)
}