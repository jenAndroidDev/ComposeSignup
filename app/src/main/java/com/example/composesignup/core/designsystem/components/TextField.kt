package com.example.composesignup.core.designsystem.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composesignup.R
import com.example.composesignup.ui.theme.GREY20

/*
* Change the trailing Icon for Password Visibility*/
@Composable
fun ComposeSignUpTextField(
    modifier: Modifier,
    containerColor:Color = Color.White,
    placeHolder:String = "Your Name",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    value:String="",
    onValueChange:(String)->Unit={}
    ){
    Column(modifier = modifier.fillMaxWidth()) {
        val isPasswordTransformation = visualTransformation==PasswordVisualTransformation()
        var passwordVisibility: Boolean by remember { mutableStateOf(false) }
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = value,
            onValueChange = {
                onValueChange.invoke(it)
            },
            shape = TextFieldShape,
            placeholder = {
                Text(
                    placeHolder,
                    style = MaterialTheme.typography.labelMedium,
                    color = GREY20
                )
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else visualTransformation,
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                unfocusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                leadingIcon?.let {
                    it.invoke()
                }
            },
            trailingIcon = {
                if (isPasswordTransformation){
                    IconButton(
                        onClick ={
                            passwordVisibility = !passwordVisibility
                        },
                        content = {
                            Icon(painter = painterResource(R.drawable.ic_success_tick),null)
                        }
                    )
                }
            }
        )
    }
}
private val CornerRadius = 8.dp
private val TextFieldShape = RoundedCornerShape(size = CornerRadius)

@Preview
@Composable
fun PreviewTextField(){
    ComposeSignUpTextField(modifier = Modifier)
}