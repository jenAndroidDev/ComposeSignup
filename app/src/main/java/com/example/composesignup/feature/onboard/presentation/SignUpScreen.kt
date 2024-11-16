package com.example.composesignup.feature.onboard.presentation



import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composesignup.core.designsystem.components.ComposeSignUpTextField
import com.example.composesignup.core.designsystem.icon.ComposeSignUpIcons
import com.example.composesignup.core.designsystem.icon.ComposeSignupVectors
import com.example.composesignup.ui.theme.GREY20
import com.example.composesignup.ui.theme.GREY80
import com.example.composesignup.ui.theme.Green40
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
    Column(modifier = modifier
        .fillMaxSize()
        .background(color = GREY20.copy(alpha = 1f))
        .padding(12.dp)
    ) {
        SignUpTextFields(
            modifier,
            action,
            viewModel
        )
        Spacer(modifier = modifier.height(16.dp))
        Validation(modifier = modifier,
            uiState)
    }
}
//is it a good practice to pass viewmodel as a parameter??
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
fun Validation(
    modifier: Modifier,
    uiState: StateFlow<SignUpUiState>
){
    val validationState = uiState.collectAsStateWithLifecycle()
    val isPasswordTyping = validationState.value.isPasswordTyping
    val isPasswordSizeValid = validationState.value.isPasswordSizeValid
    val isValidPassword = validationState.value.isCredentialsValid

    val color by animateColorAsState(targetValue = Green40, label = "color")
        Column {
            Text(
                text = "At Least 8 characters",
                style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated),
                color = if (isPasswordSizeValid)color else Color.Black)
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = "At Least One Number",
                style = MaterialTheme.typography.labelSmall,
                color = if (isValidPassword) color else Color.Black
            )
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = "Both Upper Case and Lower Case",
                style = MaterialTheme.typography.labelSmall,
                color = if (isValidPassword) color else Color.Black
            )
    }
}
//Migrate Validation List
@Composable
fun SignUpValidationList(
    modifier: Modifier,
    uiState: StateFlow<SignUpUiState>,
    action: (SignUpUiAction) -> Unit
){
    Column {
        val state = uiState.collectAsStateWithLifecycle()
        val validationMsg = state.value.validationMessages
        Log.d(Tag, "SignUpValidationList() called,${state.value.isCredentialsValid}")
        if (state.value.isCredentialsValid) {
            LazyColumn(modifier = modifier) {
                items(
                    count = validationMsg.size,
                ) {
                    ValidationItem(
                        modifier = modifier,
                        message = validationMsg[it].message,
                        isValid = validationMsg[it].isInputValid
                    )
                }
            }
        }
    }
}

@Composable
fun ValidationItem(
    modifier: Modifier,
    message:String,
    isValid:Boolean
){
    Row(modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        ) {
        Icon(
            painterResource(ComposeSignupVectors.SuccessTick),
            modifier = modifier.size(16.dp),
            contentDescription = null)
        val validState by rememberUpdatedState(isValid)
        var animatedBgColor by remember {
            mutableStateOf(validState)
        }
        if (validState)animatedBgColor = true else animatedBgColor = false
        val animatedColor by animateColorAsState(
            targetValue = if (animatedBgColor) Green40 else GREY80,
            label = "color"
        )
        Text(
            text = message,
            modifier = modifier.padding(start = 8.dp)
                .animateContentSize(
                animationSpec = tween(1000, easing = LinearEasing)
            ),
            style = MaterialTheme.typography.labelSmall,
            color = animatedColor
            )
    }

}

@Composable
fun PreviewValidationItem(){
    //ValidationItem()
}

@Preview
@Composable
fun PreviewSignUpScreen(){
    SignUpScreen()
}