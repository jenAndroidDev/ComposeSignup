package com.example.composesignup.core.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composesignup.R

@Composable
fun ComposeSignupBrandImage(modifier: Modifier = Modifier){
    Column(modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.ic_app_logo),
            modifier = modifier.wrapContentSize(),
            contentDescription = stringResource(R.string.app_logo)
        )
    }
}

@Preview
@Composable
private fun PreviewAppHeader(){
    ComposeSignupBrandImage()
}