package com.example.composesignup.feature.otpverification

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composesignup.components.ComposeSignUpButton
import com.example.composesignup.components.HyperLinkAnnotatedText
import com.example.composesignup.components.OtpVerificationSubtitle
import com.example.composesignup.feature.welcome.presentation.fontFamily
import com.example.composesignup.ui.theme.GREY20

private const val Tag = "OtpVerificationScreen"
@Composable
fun OtpVerificationScreen(modifier: Modifier){

    Column(modifier = modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        ) {
        Text(
            text = "Enter Verification Code",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = modifier.padding(top = 12.dp)
            )
        Spacer(modifier = modifier.height(12.dp))
        OtpVerificationSubtitle(helperText = "We have sent a code to", email = "rjjeninjoseph@gmail.com",
            text = "We have sent a code to rjjeninjoseph@gmail.com")
        Spacer(modifier = modifier.height(12.dp))
        EnterOtp(modifier = modifier)
        Spacer(modifier = modifier.weight(1f) )
        VerifyOtpButton(modifier = modifier)
    }//Column
}

@Composable
private fun EnterOtp(modifier: Modifier,
                     viewModel: OtpVerificationViewModel = hiltViewModel()
                     ){
    OtpView(modifier = modifier){otpValue->

        viewModel.saveEnteredOtp(otpValue)
        Log.d(Tag, "Otp Success fully completed...$otpValue")
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.enableButton){

    }


}

//complete the functionality,test and move to component package.
@Composable
fun OtpView(
    modifier: Modifier,
    otpLength:Int = 4,
    onOtpCompleted:(String)->Unit
    ){
    var otpText by remember {
        mutableStateOf("")
    }
    val keyBoardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = otpText,
        onValueChange = {
            if (it.length <= 4){
                otpText =it
            }
            if (it.length==otpLength){
                keyBoardController?.hide()
                onOtpCompleted.invoke(it)
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number),
        decorationBox = {
            Row (horizontalArrangement = Arrangement.spacedBy(10.dp)){
                repeat(4){index->//1
                    val number = when{
                        index >= otpText.length -> ""
                        else -> otpText[index]
                    }
                    Log.d(Tag, "OtpView() called with: index = $number")
                    var animateBackgroundColor by remember {
                        mutableStateOf(true)
                    }
                    LaunchedEffect(key1 = Unit) {
                        animateBackgroundColor = true
                    }
                    val animatedColor by animateColorAsState(targetValue = if (number.toString().isEmpty()) GREY20.copy(alpha = 0.1f) else GREY20.copy(alpha = 0.5f),
                        label = "color"
                    )
                    Column(
                        modifier = modifier.drawBehind {
                                 drawRoundRect(
                                     color = animatedColor,
                                     cornerRadius = CornerRadius(
                                         x=20f,y=20f
                                     ))
                        },
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = number.toString(),
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black
                        )
                        Box(modifier = modifier
                            .width(40.dp)
                            .height(1.dp)
                            .clip(shape = RoundedCornerShape(12.dp))
                            .background(color = GREY20))
                    }
                }
            }
        }
    )
}
@Composable
fun VerifyOtpButton(modifier: Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ComposeSignUpButton(
            modifier = modifier,
            text = "Verify Now",
            backgroundColor = GREY20,
            borderColor = GREY20,
            textColor = Color.Black.copy(alpha = 0.5f)
        ) {

        } 
        Spacer(modifier = modifier.height(12.dp))
        HyperLinkAnnotatedText(
            modifier = modifier,
            text = "Didn't receive otp? Resend Code",
            urlAnnotatedText = "Resend Code"){
            Log.d(Tag, "VerifyOtpButton() called")
        }
        Spacer(modifier = modifier.height(12.dp))
    }
}


@Preview
@Composable
fun OtpVerificationScreenPreview(){
    OtpVerificationScreen(modifier = Modifier)
}