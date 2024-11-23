package com.example.composesignup.feature.onboard.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.material.tabs.TabItem

@Composable
fun OnboardScreen(
    modifier: Modifier,

){
    Column {

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnboardTab(modifier: Modifier){
    val tabItems = listOf(
        OnboardTabItem(
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            "Account"
        ),
        OnboardTabItem(
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            "Payment Method"
        ))
    val pagerState = rememberPagerState(pageCount = { tabItems.size })

}