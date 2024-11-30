package com.example.composesignup.feature.onboard.presentation


import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.composesignup.ui.theme.GREY20
import com.example.composesignup.ui.theme.Green40
import com.example.composesignup.ui.theme.Green80
import com.example.composesignup.ui.theme.GreyWhite
import kotlinx.coroutines.flow.StateFlow

/*
* 3.Navigate To Dashboard Screen
* 4.Replace TrailIcon in Text Field Screen
* 6.Design Alignment
* 7.Unit Test and Check For Any Recompositions During State Changes
* */
private val TopPadding = 24.dp
private val TextFieldPadding = 16.dp
private const val Tag = "SignUpScreen"

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    action: (SignUpUiAction) -> Unit = viewModel.action,
    uiState: StateFlow<SignUpUiState> = viewModel.uiState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = GreyWhite)
            .padding(12.dp)
    ) {
        SignUpTextFields(
            modifier = modifier,
            action = action,
            viewModel = viewModel
        )
        Spacer(modifier = modifier.height(16.dp))
        Validation(
            modifier = modifier,
            uiState = uiState
        )
        Spacer(modifier = modifier.weight(1f))
        TermsAndCondition(
            modifier = modifier,
            uiState = uiState,
            action = action
        )
        Spacer(modifier = modifier.height(12.dp))
        ComposeSignUpButton(
            text = stringResource(id = R.string.sign_up)
        ) {
            action.invoke(SignUpUiAction.SignUp)
        }
        Spacer(modifier = modifier.height(12.dp))
        UiError(
            uiState = uiState,
            action = action
        )
    }//Column
}

//is it a good practice to pass viewmodel as a parameter??
@Composable
fun SignUpTextFields(
    modifier: Modifier,
    action: (SignUpUiAction) -> Unit,
    viewModel: SignUpViewModel
) {
    Column {
        Spacer(modifier = modifier.height(TopPadding))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = stringResource(id = R.string.name_hint),
            value = viewModel.userName,
            leadingIcon = {
                Icon(imageVector = ComposeSignUpIcons.Profile, contentDescription = null)
            }
        ) {
            action.invoke(SignUpUiAction.UserName(it))
        }//:TextField=>User Name
        Spacer(modifier = modifier.height(TextFieldPadding))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = stringResource(id = R.string.email_hint),
            value = viewModel.email,
            leadingIcon = {
                Icon(imageVector = ComposeSignUpIcons.Email, contentDescription = null)
            }
        ) {
            action.invoke(SignUpUiAction.Email(it))
        }//:TextField=>User Email
        Spacer(modifier = modifier.height(TextFieldPadding))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = stringResource(id = R.string.password_hint),
            leadingIcon = {
                Icon(imageVector = ComposeSignUpIcons.PasswordLock, contentDescription = null)
            },
            trailingIcon = {
                Icon(
                    imageVector = ComposeSignUpIcons.PasswordEye,
                    contentDescription = "toggle Password Visibility"
                )
            },
            value = viewModel.password,
            visualTransformation = PasswordVisualTransformation()
        ) {
            action.invoke(SignUpUiAction.Password(it))
        }//:TextField=>Password
        Spacer(modifier = modifier.height(TextFieldPadding))
        ComposeSignUpTextField(
            modifier = modifier,
            placeHolder = stringResource(id = R.string.password_confirm_hint),
            value = viewModel.confirmPassword,
            leadingIcon = {
                Icon(imageVector = ComposeSignUpIcons.PasswordLock, contentDescription = null)
            },
            trailingIcon = {
                Icon(
                    imageVector = ComposeSignUpIcons.PasswordEye,
                    contentDescription = "toggle Password Visibility"
                )
            },
            visualTransformation = PasswordVisualTransformation()
        ) {
            action.invoke(SignUpUiAction.ConfirmPassword(it))
        }//:TextField=>ConfirmPassword

    }//:Column
}

@Composable
fun Validation(
    modifier: Modifier,
    uiState: StateFlow<SignUpUiState>
) {
    val validationState = uiState.collectAsStateWithLifecycle()
    val isPasswordSizeValid = validationState.value.isPasswordSizeValid
    val isValidPassword = validationState.value.isCredentialsValid
    Column {
        val color by animateColorAsState(targetValue = Green40, label = "color")
        Text(
            text = "At Least 8 characters",
            style = MaterialTheme.typography.labelSmall,
            color = if (isPasswordSizeValid) color else Color.Black
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = "At Least One Number",
            style = MaterialTheme.typography.labelSmall,
            color = if (isValidPassword) color else Color.Black
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = "Both Upper Case and Lower Case",
            style = MaterialTheme.typography.labelSmall,
            color = if (isValidPassword) color else Color.Black
        )
    }//Column
}

//Migrate Validation List
@Composable
private fun TermsAndCondition(
    modifier: Modifier,
    uiState: StateFlow<SignUpUiState>,
    action: (SignUpUiAction) -> Unit
) {
    val termsAcceptedState = uiState.collectAsStateWithLifecycle()
    val isTermsAccepted = termsAcceptedState.value.isTermsAccepted
    Row(
        modifier = modifier.padding(start = 6.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = isTermsAccepted,
            onCheckedChange = {
                action.invoke(SignUpUiAction.ToggleTermsAndCondition)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Green40,
                uncheckedThumbColor = Green40.copy(alpha = 0.2f),
                checkedTrackColor = Green80,
                uncheckedTrackColor = Green80.copy(alpha = 0.4f),
            )
        )
        Spacer(modifier.padding(12.dp))
        Text(
            text = stringResource(id = R.string.terms_and_conditions),
            style = MaterialTheme.typography.labelMedium,
        )
    }//:Row
}
@Composable
fun UiError(
    uiState: StateFlow<SignUpUiState>,
    action: (SignUpUiAction) -> Unit
){
    val isInputValid = uiState.collectAsStateWithLifecycle().value.isInputValid
    val hasException = uiState.collectAsStateWithLifecycle().value.exception
    if (hasException!=null){
        when(hasException){
            is TextFieldException->{
                val (e,uiErr) =uiState.value.exception to uiState.value.uiText
                if (e!=null){
                    Toast.makeText(LocalContext.current,uiErr?.asString(LocalContext.current),Toast.LENGTH_LONG).show()
                    action.invoke(SignUpUiAction.UiErrorShown)
                }
            }
        }
    }
    if (isInputValid){
        Toast.makeText(LocalContext.current,"Form Valid",Toast.LENGTH_LONG).show()
    }

}


@Preview
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen()
}

