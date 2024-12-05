package com.example.composesignup.feature.onboard.presentation


import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composesignup.R
import com.example.composesignup.core.designsystem.components.ComposeSignupOnBoardHeader
import com.example.composesignup.core.designsystem.components.ComposeSignupBrandImage
import com.example.composesignup.core.designsystem.components.ComposeSignUpButton
import com.example.composesignup.core.designsystem.components.ComposeSignUpTextField
import com.example.composesignup.core.designsystem.icon.ComposeSignUpIcons
import com.example.composesignup.core.utils.TextFieldException
import com.example.composesignup.ui.theme.GREY20
import com.example.composesignup.ui.theme.Green80
import com.example.composesignup.ui.theme.fontFamily
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier,
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    uiState:StateFlow<ForgotPasswordUiState> = viewModel.uiState,
    uiAction:(ForgotPasswordUiAction)->Unit = viewModel.action
    ){
    Column(modifier = modifier.fillMaxSize()
        .background(color = Color.White)
        ) {
        ComposeSignupOnBoardHeader(
            modifier = modifier,
            title = stringResource(R.string.forgot_password_title),
            description = stringResource(R.string.forgot_password_description)
        )
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
            uiAction.invoke(ForgotPasswordUiAction.Continue)
        }
        Spacer(modifier = modifier.height(12.dp))
        ComposeSignUpButton (
            modifier = modifier,
            backgroundColor = GREY20,
            text = "Cancel",
            borderColor = Color.Black.copy(alpha = 0.1f),
            textColor = Color.Black
        ){
            uiAction.invoke(ForgotPasswordUiAction.Cancel)
        }
        UiError(
            uiState = uiState,
            uiAction = uiAction
        )
        Spacer(modifier = modifier.weight(1f))

    }
}
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
@Composable
private fun UiError(
    uiState: StateFlow<ForgotPasswordUiState>,
    uiAction: (ForgotPasswordUiAction) -> Unit
){
    val hasExceptions = uiState.collectAsStateWithLifecycle().value.exception
    if (hasExceptions!=null){
        when(hasExceptions){
            is TextFieldException->{
                val (e,uiErr) = uiState.value.exception to uiState.value.uiText
                if (e!=null){
                    Toast.makeText(
                        LocalContext.current,uiErr?.asString(LocalContext.current),
                        Toast.LENGTH_LONG).show()
                    uiAction.invoke(ForgotPasswordUiAction.UiErrorShown)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ForgotPasswordPreview(){
    ForgotPasswordScreen(modifier = Modifier)
}