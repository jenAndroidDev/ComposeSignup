package com.example.composesignup.feature.onboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composesignup.components.ComposeSignUpTextField
import com.example.composesignup.ui.theme.GREY20
import com.example.composesignup.ui.theme.GREY80

private val TopPadding = 40.dp
private val TextFieldPadding = 16.dp
private const val Tag = "SignUpScreen"
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    action:(SignUpUiAction)->Unit = viewModel.action
){
    Column(modifier = modifier.fillMaxSize()
        .background(color = GREY20.copy(alpha = 1f))
        .padding(12.dp)
    ) {
        Spacer(modifier = modifier.height(TopPadding))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = "Your Name",
            value = viewModel.userName
            ){
            action.invoke(SignUpUiAction.UserName(it))
        }
        Spacer(modifier = modifier.height(TextFieldPadding))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = "Enter Your Email",
            value = viewModel.email
            ){
            action.invoke(SignUpUiAction.Email(it))
        }
        Spacer(modifier = modifier.height(TextFieldPadding))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = "Enter Your Password",
            value = viewModel.password,
            visualTransformation = PasswordVisualTransformation()){
            action.invoke(SignUpUiAction.Password(it))
        }
        Spacer(modifier = modifier.height(TextFieldPadding))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = "Confirm Your Password",
            value = viewModel.confirmPassword,
            visualTransformation = PasswordVisualTransformation()){
            action.invoke(SignUpUiAction.ConfirmPassword(it))
        }
    }
}

@Preview
@Composable
fun PreviewSignUpScreen(){
    SignUpScreen()
}