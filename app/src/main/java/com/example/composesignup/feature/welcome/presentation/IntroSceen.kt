package com.example.composesignup.feature.welcome.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import com.example.composesignup.R
import com.example.composesignup.components.ComposeSignUpButton
import com.example.composesignup.components.wormTransition
import com.example.composesignup.ui.theme.GREY20
import com.example.composesignup.ui.theme.Green40
import com.example.composesignup.ui.theme.Green80
import java.util.UUID

/*
*
* Scale up animation
* */

@Composable
fun IntroScreen(modifier: Modifier = Modifier,
                onClick:()->Unit = {}){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Title(modifier)
        Spacer(modifier = modifier.height(24.dp))
        IntroSlider(modifier = modifier)
        Spacer(modifier = modifier.weight(1f))
        ComposeSignUpButton(text = "Next",
            textColor = Green80,
            backgroundColor = Green40) {
            onClick.invoke()
        }
        Spacer(modifier = modifier.height(12.dp))
        ComposeSignUpButton(text = "Skip",
            textColor = Green80,
            backgroundColor = Color.White,
            borderColor = Color.Gray.copy(alpha = 0.2f),
            borderWidth = 2.dp
        ) {
            onClick.invoke()
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
fun IntroSlider(modifier: Modifier = Modifier){
    //move this list to viewmodel.
    val items = arrayListOf(
        IntroItem(UUID.randomUUID().toString(),
            R.drawable.pixelcut_export,
            "Team Up For Success",
            "Get ready to unleash your witness the  power of teamwork as we embark on this extraordinary project."
        ),
        IntroItem(UUID.randomUUID().toString(),
            R.drawable.pixelcut_export_2,
            "User-Friendly at its Core",
            "Discover the essence of user friendly ness" +
                    "as our interface empowers you with intuitive controls and effortless interactions."
        ),
        IntroItem(UUID.randomUUID().toString(),
            R.drawable.pixelcut_export_3,
            "Easy Task Creation",
            "Quickly Add tasks,set due dates and add descriptions with ease" +
                    "using our task manager app.Simplify your workflow and stay organised."
        )
    )
    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){

        val pagerState = rememberPagerState {
            items.size
        }
        HorizontalPager(
            state =pagerState,
        ) {page->
            IntroItem(model = items[page], modifier = modifier)
        }//:Horizontal Pager
        WormIndicator(count = items.size, pagerState = pagerState)
        Text(text = items[pagerState.currentPage].title,
                color = Green80,
                style = MaterialTheme.typography.titleLarge,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        Spacer(modifier = modifier.height(12.dp))
        Text(
            text = items[pagerState.currentPage].description,
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