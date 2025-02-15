package com.example.composesignup.feature.onboard.presentation


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composesignup.R
import com.example.composesignup.core.designsystem.components.ComposeSignupOnBoardHeader
import com.example.composesignup.ui.theme.GREY20
import com.example.composesignup.ui.theme.Green80
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/*
* 1.Enable Edge to Edge*/
private const val Tag = "OnboardScreen"

@Composable
fun OnboardScreen(
    modifier: Modifier,
    onForgotPasswordClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel:OnboardScreenViewModel = hiltViewModel(),
    uiState:StateFlow<OnboardUiState> = viewModel.uiState

) {
    val state = uiState.collectAsStateWithLifecycle().value.isOnboardShown
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)

    ) {
        ComposeSignupOnBoardHeader(
            modifier = modifier,
            title = stringResource(R.string.welcome_text),
            description = stringResource(R.string.onboard_description)
        )
        OnboardTabContainer(modifier = Modifier,
            onForgotPasswordClick = onForgotPasswordClick,
            onLoginSuccess = onLoginSuccess)
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnboardTabContainer(
    modifier: Modifier,
    onForgotPasswordClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val tabItems = listOf(
        OnboardTabItem(
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            description = stringResource(R.string.login)
        ),
        OnboardTabItem(
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            description = stringResource(R.string.sign_up)
        )
    )
    val pagerState = rememberPagerState(pageCount = { tabItems.size })
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(RoundedCornerRadius),
                color = Color.White
            )
    ) {
        val coroutineScope = rememberCoroutineScope()
        TabRow(
            containerColor = Color.White,
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                if (pagerState.currentPage < tabItems.size) {
                    TabRowDefaults.Indicator(
                        color = Green80,
                        modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    )
                }
            }
        ) {
            tabItems.onEachIndexed { index, onboardTabItem ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    },
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
                0 -> {
                    LoginScreen(
                        modifier = modifier,
                        onForgotPasswordClick = onForgotPasswordClick,
                        onLoginSuccess = onLoginSuccess
                    )
                }

                1 -> {
                    SignUpScreen(modifier = modifier)
                }
            }
        }
    }
}
private val RoundedCornerRadius = 12.dp
@Preview
@Composable
private fun PreviewOnboardScreen(){
    OnboardScreen(modifier = Modifier, onForgotPasswordClick = {}, onLoginSuccess = {})
}

