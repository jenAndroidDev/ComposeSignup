package com.example.composesignup.core.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.composesignup.ui.theme.GREY20
import com.example.composesignup.ui.theme.GREY80
import com.example.composesignup.ui.theme.Green80

//rename this function.
@Composable
fun OtpVerificationSubtitle(
    text:String,
    email:String,
    helperText:String,
    ){
    val annotatedText = buildAnnotatedString {
        val startIndex = text.indexOf(helperText)
        val endIndex = startIndex+email.length
        append(text)
        addStyle(style = SpanStyle(color = GREY20), start = startIndex, end = endIndex)

        val email = email
        val boldStartIndex = text.indexOf(email)
        val boldEndIndex = startIndex+email.length
        addStyle(style = SpanStyle(color = Color.Black), start = boldStartIndex, end = boldEndIndex)
    }
    Text(annotatedText)
}

@Composable
fun HyperLinkAnnotatedText(
    modifier: Modifier,
    text: String,
    urlAnnotatedText:String,
    onClick:()->Unit
    ){
    val annotatedText = buildAnnotatedString {
        val startIndex = text.indexOf(urlAnnotatedText)
        val endIndex = startIndex + urlAnnotatedText.length
        append(text)
        addStyle(style = SpanStyle(color = Green80), start = startIndex, end = endIndex)

        val clickableStartIndex = text.indexOf(urlAnnotatedText)
        val clickableEndIndex = startIndex + urlAnnotatedText.length
        addStyle(
            style = SpanStyle(
                color = Green80,
                textDecoration = TextDecoration.Underline,
                ),
            start = clickableStartIndex,
            end = clickableEndIndex
        )
        append()
    }
    ClickableText(text = annotatedText,
        modifier = modifier.padding(6.dp),
        ) {
        onClick.invoke()
    }


}