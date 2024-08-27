package com.example.composesignup.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composesignup.ui.theme.Green40
import com.example.composesignup.ui.theme.Green80

@Composable
fun ComposeSignUpButton(
    modifier: Modifier = Modifier,
    shape: androidx.compose.ui.graphics.Shape = ButtonShape,
    text:String = "Next",
    textColor:Color= Green80,
    backgroundColor: Color = Green40,
    borderColor:Color = Green40,
    borderWidth: Dp = 2.dp,
    onClick:()->Unit,
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
            .height(ButtonHeight)
            .padding(start = 16.dp, end = 16.dp)
            .background(color = backgroundColor, shape = shape)
            .border(border = BorderStroke(width = borderWidth, color = borderColor),
                shape = shape)
            .clickable {
                onClick.invoke()
            }
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.bodyLarge,
            )
    }
}
private val ButtonShape = RoundedCornerShape(size = 10.dp)
private val ButtonHeight = 50.dp

@Preview
@Composable
fun ComposSignUpButtonPreview(){
    ComposeSignUpButton {

    }
}
