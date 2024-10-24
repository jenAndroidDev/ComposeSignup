package com.example.composesignup.feature.onboard.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composesignup.core.designsystem.components.ComposeSignUpTextField
import com.example.composesignup.core.designsystem.icon.ComposeSignUpIcons
import com.example.composesignup.core.designsystem.icon.ComposeSignupVectors
import com.example.composesignup.ui.theme.GREY20
import com.example.composesignup.ui.theme.GREY80
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
fun SignUpValidationList(
modifier: Modifier,
viewModel: SignUpViewModel,
action: (SignUpUiAction) -> Unit
){
    Column {
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        val validationMsg = uiState.value.validationMessages
        LazyColumn(modifier = modifier) {
            items(count = validationMsg.size,
                ){

            }
        }
    }

}

@Composable
fun ValidationItem(
    modifier: Modifier = Modifier,
    message:String = "Atleast 8 Characters",
    isValid:Boolean = false
){
    Row(modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        ) {
        Icon(
            painterResource(ComposeSignupVectors.SuccessTick),
            modifier = modifier.size(18.dp),
            contentDescription = null)
        Text(text = message,
            modifier = modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.labelSmall,
            color = GREY80
            )
    }

}

@Preview
@Composable
fun PreviewValidationItem(){
    ValidationItem()
}

@Preview
@Composable
fun PreviewSignUpScreen(){
    SignUpScreen()
}