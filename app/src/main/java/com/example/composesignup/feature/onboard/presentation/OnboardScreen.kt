package com.example.composesignup.feature.onboard.presentation

import android.preference.PreferenceActivity.Header
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composesignup.R
import com.example.composesignup.ui.theme.GREY20
import com.example.composesignup.ui.theme.Green80
import kotlinx.coroutines.launch

@Composable
fun OnboardScreen(
    modifier: Modifier,

){
    Column(modifier = modifier.fillMaxSize()
        .background(color = GREY20)) {
        Header(modifier = modifier)
        Spacer(modifier = modifier.height(8.dp))
        OnboardTabContainer(modifier = Modifier)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnboardTabContainer(modifier: Modifier){
    val tabItems = listOf(
        OnboardTabItem(
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            "Login"
        ),
        OnboardTabItem(
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            "Sign up"
        ))
    val pagerState = rememberPagerState(pageCount = { tabItems.size })
    Column(modifier = modifier.fillMaxWidth()) {
        val coroutineScope = rememberCoroutineScope()
        TabRow(
            containerColor = GREY20,
            selectedTabIndex = pagerState.currentPage,
            indicator = {tabPositions ->
                if (pagerState.currentPage<tabItems.size) {
                    TabRowDefaults.Indicator(
                        color = Green80,
                        modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    )
                }
            }
        ) {
            tabItems.onEachIndexed { index, onboardTabItem ->
                Tab(
                  selected = pagerState.currentPage==index,
                  onClick = {
                      coroutineScope.launch { pagerState.animateScrollToPage(index) }
                  } ,
                  text = {
                      Text(text = onboardTabItem.description)
                  },
                  selectedContentColor = Green80,
                  unselectedContentColor = Color.Black
                )
            }
        }
        HorizontalPager(
            modifier = Modifier.background(color = GREY20),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { index ->
            when (index) {
                0->{
                   LoginScreen(modifier = modifier)
                }
                1->{
                    SignUpScreen(modifier = modifier)
                }
            }
        }
    }
}
@Composable
private fun Header(modifier: Modifier){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.welcome_text),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = modifier.height(12.dp))
        Text(
            text = stringResource(R.string.onboard_hint),
            style = MaterialTheme.typography.labelSmall
        )

    }
}