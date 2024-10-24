package com.example.composesignup.feature.onboard.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composesignup.core.designsystem.components.ComposeSignUpTextField
import com.example.composesignup.core.designsystem.icon.ComposeSignUpIcons
import com.example.composesignup.ui.theme.GREY20
import kotlinx.coroutines.flow.StateFlow

/*
* Do not hardcode the strings*/
private val TopPadding = 40.dp
private val TextFieldPadding = 16.dp
private const val Tag = "SignUpScreen"
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    action:(SignUpUiAction)->Unit = viewModel.action,
    uiState:StateFlow<SignUpUiState> = viewModel.uiState
){
    Column(modifier = modifier.fillMaxSize()
        .background(color = GREY20.copy(alpha = 1f))
        .padding(12.dp)
    ) {
        SignUpTextFields(
            modifier, action, viewModel
        )
    }
}
@Composable
fun SignUpTextFields(
    modifier: Modifier,
    action: (SignUpUiAction) -> Unit,
    viewModel: SignUpViewModel
){
    Column {
        Spacer(modifier = modifier.height(TopPadding))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = "Your Name",
            value = viewModel.userName,
            leadingIcon = {
                Icon(imageVector = ComposeSignUpIcons.Profile, contentDescription = null)
            }
        ){
            action.invoke(SignUpUiAction.UserName(it))
        }
        Spacer(modifier = modifier.height(TextFieldPadding))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = "Enter Your Email",
            value = viewModel.email,
            leadingIcon = {
                Icon(imageVector = ComposeSignUpIcons.Email,contentDescription = null)
            }
        ){
            action.invoke(SignUpUiAction.Email(it))
        }
        Spacer(modifier = modifier.height(TextFieldPadding))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = "Enter Your Password",
            leadingIcon = {
                Icon(imageVector = ComposeSignUpIcons.PasswordLock, contentDescription = null)
            },
            value = viewModel.password,
            visualTransformation = PasswordVisualTransformation()){
            action.invoke(SignUpUiAction.Password(it))
        }
        Spacer(modifier = modifier.height(TextFieldPadding))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = "Confirm Your Password",
            value = viewModel.confirmPassword,
            leadingIcon = {
                Icon(imageVector = ComposeSignUpIcons.PasswordLock,contentDescription = null)
            },
            visualTransformation = PasswordVisualTransformation()){
            action.invoke(SignUpUiAction.ConfirmPassword(it))
        }
    }
}
@Composable
fun SignUpValidation(
viewModel: SignUpViewModel,
action: (SignUpUiAction) -> Unit
){
    Column {
        LazyColumn {

        }
    }

}

@Preview
@Composable
fun PreviewSignUpScreen(){
    SignUpScreen()
}