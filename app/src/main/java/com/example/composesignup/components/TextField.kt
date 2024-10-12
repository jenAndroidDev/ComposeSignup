package com.example.composesignup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composesignup.ui.theme.GREY20
import com.example.composesignup.ui.theme.Green40

@Composable
fun ComposeSignUpTextField(
    modifier: Modifier,
    backgroundColor:Color = Color.White,
    hint:String = "Enter your name",
    imeAction: ImeAction = ImeAction.Next,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    enableTrailingIcon:Boolean = false,
    enableLeadingIcon:Boolean = false,
    ){

    Column(modifier = modifier.fillMaxWidth()) {

    }

    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }

    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        shape = TextFieldShape,
        placeholder = {
            Text(
                hint,
                style = MaterialTheme.typography.labelMedium,
                color = GREY20
                )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )

    )

}
private val CornerRadius = 10.dp
private val TextFieldShape = RoundedCornerShape(size = CornerRadius )



@Preview
@Composable
fun PreviewTextField(){
    ComposeSignUpTextField(modifier = Modifier)
}