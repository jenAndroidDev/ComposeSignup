package com.example.composesignup.feature.onboard.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composesignup.R
import com.example.composesignup.core.designsystem.components.ComposeSignUpTextField
import kotlinx.coroutines.flow.StateFlow

@Composable
fun LoginScreen(
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    uiState:StateFlow<LoginUiState> = viewModel.uiState,
    uiAction: (LoginUiAction)->Unit = viewModel.action
) {
    Column(modifier = modifier.fillMaxSize()) {
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = stringResource(R.string.email_hint)
        ){

        }
        Spacer(modifier = modifier.height(8.dp))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = stringResource(R.string.password_hint)
        ){

        }

    }


}

@Preview
@Composable
private fun LoginScreenPreview(){
    LoginScreen(modifier = Modifier)
}