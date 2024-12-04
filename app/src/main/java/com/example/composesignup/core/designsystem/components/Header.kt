package com.example.composesignup.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composesignup.R
import com.example.composesignup.ui.theme.fontFamily

@Composable
fun ComposSignupOnBoardHeader(
    modifier: Modifier,
    title:String="",
    description:String=""
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(24.dp))
        ComposeSignupBrandImage(modifier = modifier.padding(end = 8.dp))
        Spacer(modifier = modifier.height(18.dp))
        Text(
            text = stringResource(R.string.welcome_text),
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = stringResource(R.string.onboard_hint),
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            style = MaterialTheme.typography.labelMedium,
            color = Color.DarkGray,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(20.dp))
    }
}