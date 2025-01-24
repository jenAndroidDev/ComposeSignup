package com.example.composesignup.feature.onboard.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composesignup.R
import com.example.composesignup.core.designsystem.components.ComposeSignUpButton
import com.example.composesignup.core.designsystem.components.ComposeSignUpTextField
import com.example.composesignup.core.designsystem.icon.ComposeSignUpIcons
import com.example.composesignup.core.utils.TextFieldException
import com.example.composesignup.ui.theme.GreyWhite
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

/*
* TODO==>Form Validation
*  2.Trailing Icon in Text Fields
*  3.*/
private const val Tag = "LoginScreen"
@Composable
fun LoginScreen(
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    uiState: StateFlow<LoginUiState> = viewModel.uiState,
    uiAction: (LoginUiAction) -> Unit = viewModel.action,
    onForgotPasswordClick: () -> Unit = {},
    onLoginSuccess: () -> Unit
) {
    val isInputValid = uiState.collectAsStateWithLifecycle().value.isValid
    if(isInputValid)onLoginSuccess.invoke()
    Column(modifier  = modifier
        .fillMaxSize()
        .background(color = GreyWhite)
        .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginTextFields(
            modifier = modifier,
            viewModel = viewModel,
            uiAction = uiAction,
            onForgotPasswordClick = onForgotPasswordClick
        )
        Spacer(modifier = modifier.height(12.dp))
        UiError(uiState = uiState,uiAction)
    }
}
@Composable
private fun LoginTextFields(
    modifier: Modifier,
    viewModel: LoginViewModel,
    uiAction: (LoginUiAction) -> Unit,
    onForgotPasswordClick: () -> Unit
){
    val navigateToForgotPasswordScreen = viewModel.uiState.collectAsStateWithLifecycle().value.navToPasswordScreen
    if (navigateToForgotPasswordScreen){
        Timber.tag(Tag)
            .d("LoginTextFields() called with: modifier = " + modifier + ", viewModel = " + viewModel + ", uiAction = " + uiAction + ", onForgotPasswordClick = " + navigateToForgotPasswordScreen)
        onForgotPasswordClick.invoke()
        uiAction.invoke(LoginUiAction.ResetNavOptions)
    }
    Column(modifier = modifier.fillMaxSize()) {
        Spacer(modifier = modifier.height(24.dp))
        ComposeSignUpTextField(
            modifier = modifier,
            value = viewModel.email,
            placeHolder = stringResource(R.string.email_hint),
            leadingIcon = {
                Icon(imageVector = ComposeSignUpIcons.Email, contentDescription = "Email")
            }
        ) {
            uiAction.invoke(LoginUiAction.Email(it))
        }
        Spacer(modifier = modifier.height(16.dp))
        ComposeSignUpTextField(
            modifier = modifier,
            value = viewModel.password,
            placeHolder = stringResource(R.string.password_hint),
            leadingIcon = {
                Icon(imageVector = ComposeSignUpIcons.PasswordLock, contentDescription = "Password")
            },
            visualTransformation = PasswordVisualTransformation()
        ) {
            uiAction.invoke(LoginUiAction.Password(it))
        }
        Spacer(modifier = modifier.height(12.dp))
        Text(text = "Forgot Password?",
            style = MaterialTheme.typography.labelMedium,
            modifier = modifier.clickable {
                uiAction.invoke(LoginUiAction.ForgotPassword)
            })
        Spacer(modifier = modifier.weight(1f))
        ComposeSignUpButton(
            modifier = modifier,
            text = "Login"
            ) {
            uiAction.invoke(LoginUiAction.Login)
        }
    }//:Column

}
@Composable
private fun UiError(
    uiState: StateFlow<LoginUiState>,
    action: (LoginUiAction)->Unit
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
                    action.invoke(LoginUiAction.UiErrorShown)
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview(){
    LoginScreen(modifier = Modifier, onLoginSuccess = {})
}