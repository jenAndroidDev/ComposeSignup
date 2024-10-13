package com.example.composesignup.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composesignup.ui.theme.GREY20

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
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor
            ),
            leadingIcon = {
                leadingIcon?.let { it() }
            },
            trailingIcon = {
                trailingIcon?.let { it() }
            }
        )
    }
}
private val CornerRadius = 8.dp
private val TextFieldShape = RoundedCornerShape(size = CornerRadius )

@Preview
@Composable
fun PreviewTextField(){
    ComposeSignUpTextField(modifier = Modifier)
}