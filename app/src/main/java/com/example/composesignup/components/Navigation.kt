package com.example.composesignup.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composesignup.ui.theme.GREY20

@Composable
fun ComposeSignUpNavigationBar(
    modifier: Modifier,
    content:@Composable RowScope.()->Unit
){
    NavigationBar(
        modifier = modifier,
        containerColor = Color.Black.copy(alpha = 0.4f),
        contentColor = GREY20.copy(alpha = 0.3f),
        tonalElevation = 0.dp,
        content = content,
    )
}

@Composable
fun RowScope.ComposeSignUpBarItem(
    selected:Boolean,
    onClick:()->Unit,
    modifier: Modifier = Modifier,
    enabled:Boolean = true,
    alwaysShowLabel:Boolean =true,
    icon:@Composable ()->Unit,
    selectedIcon:@Composable ()->Unit = icon,
    label: @Composable (()->Unit)?=null
){
    NavigationBarItem(
        modifier = modifier,
        selected = selected,
        onClick = { onClick.invoke() },
        icon = { Icons.Rounded.Home},
        enabled = enabled,
        alwaysShowLabel = alwaysShowLabel,
        label = {
            Text(text = "For You Testing")
        },
        )

}