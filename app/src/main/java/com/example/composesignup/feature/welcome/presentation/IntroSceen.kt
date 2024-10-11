package com.example.composesignup.feature.welcome.presentation

import android.nfc.Tag
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composesignup.R
import com.example.composesignup.components.ComposeSignUpButton
import com.example.composesignup.components.wormTransition
import com.example.composesignup.ui.theme.GREY20
import com.example.composesignup.ui.theme.Green40
import com.example.composesignup.ui.theme.Green80
import kotlinx.coroutines.flow.map
import java.util.UUID

/*
*
* Scale up animation
* */
private const val Tag = "IntroScreen"
@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    onClick:()->Unit = {},
    viewModel: IntroScreenViewModel = hiltViewModel(),
    uiAction:(IntroScreenUiAction)->Unit = viewModel.action
    ){

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val isSlidingComplete = uiState.value.isWelcomeSlideCompleted
    if (isSlidingComplete){
        Log.d(
            Tag,
            "IntroScreen() called with: modifier = $modifier, onClick = $onClick, viewModel = $viewModel, uiAction = $uiAction"
        )
        onClick.invoke()
        uiAction.invoke(IntroScreenUiAction.RefreshInternal)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Title(modifier)
        Spacer(modifier = modifier.height(24.dp))
        IntroSlider(
            modifier = modifier,
            viewModel = viewModel
            )
        Spacer(modifier = modifier.weight(1f))
        ComposeSignUpButton(text = "Next",
            textColor = Green80,
            backgroundColor = Green40) {
            uiAction.invoke(IntroScreenUiAction.IntroScreenSlideCompleted)
        }
        Spacer(modifier = modifier.height(12.dp))
        ComposeSignUpButton(text = "Skip",
            textColor = Green80,
            backgroundColor = Color.White,
            borderColor = Color.Gray.copy(alpha = 0.2f),
            borderWidth = 2.dp
        ) {
            uiAction.invoke(IntroScreenUiAction.IntroScreenSkipped)
        }
        Spacer(modifier = modifier.height(12.dp))
    }
}
@Composable
private fun Title(modifier: Modifier){
    Text(
        text = "TaskForce",
        modifier =modifier.padding(top = 12.dp) ,
        style = MaterialTheme.typography.titleLarge,
        color = Green80,
        fontFamily = fontFamily,
        fontWeight = FontWeight.ExtraBold
        )
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroSlider(
    modifier: Modifier = Modifier,
    viewModel: IntroScreenViewModel,
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val introSlideItems = uiState.value.data
    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){

        val pagerState = rememberPagerState {
            introSlideItems.size
        }
        HorizontalPager(
            state =pagerState,
        ) {page->
            IntroItem(model = introSlideItems[page], modifier = modifier)
        }//:Horizontal Pager
        WormIndicator(count = introSlideItems.size, pagerState = pagerState)
        Text(text = introSlideItems[pagerState.currentPage].title,
                color = Green80,
                style = MaterialTheme.typography.titleLarge,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        Spacer(modifier = modifier.height(12.dp))
        Text(
            text = introSlideItems[pagerState.currentPage].description,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            color = GREY20,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(
                start = 10.dp,
                end = 10.dp
            )
        )//:Text
    }//:Column
}

@Composable
fun IntroItem(
    model:IntroItem,
    modifier: Modifier
    ){
    Image(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f),
        painter = painterResource(id = model.image),
        contentDescription = "intro_item",
        contentScale = ContentScale.Fit
    )
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WormIndicator(
    count: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    spacing: Dp = 10.dp,
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            modifier = modifier
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(count) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(
                            color = Color.Gray,
                            shape = CircleShape
                        )

                )
            }
        }//Row
        Box(
            Modifier
                .wormTransition(pagerState)
                .size(12.dp)
        )
    }//:Box
}



@Preview
@Composable
fun TitlePreview(){
    Title(modifier = Modifier)
}
@Preview
@Composable
fun IntroScreenPreview(){
    IntroScreen()
}
@Preview
@Composable
fun IntroItemPreview(){
    IntroItem(model = IntroItem(
        UUID.randomUUID().toString(),
        title = "Sample",
        description = "",
        ),
        modifier = Modifier
    )
}
//move this to Type.kt file in theme package.
val fontFamily = FontFamily(
    Font(
        R.font.opensans_semibold,
        weight = FontWeight.SemiBold,
    ),
    Font(
        R.font.opensans_medium,
        weight = FontWeight.Medium
    ),
    Font(
        R.font.opensans_extrabold,
        weight = FontWeight.ExtraBold
    ),
    Font(
        R.font.opensans_bold,
        weight = FontWeight.Bold
    )
)