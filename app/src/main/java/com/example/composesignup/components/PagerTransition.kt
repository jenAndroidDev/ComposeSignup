package com.example.composesignup.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.example.composesignup.ui.theme.Green80

private const val Tag = "PagerTransition"
@OptIn(ExperimentalFoundationApi::class)
fun Modifier.wormTransition(
    pagerState: PagerState
) = run {
    drawBehind {
        val distance = size.width +10.dp.roundToPx()
        Log.d(Tag, "wormTransition() called...${size.width},${size.height}")
        val scrollPosition = pagerState.currentPage + pagerState.currentPageOffsetFraction
        Log.d(Tag, "wormTransition() called " +
                "${pagerState.currentPage}," +
                "${pagerState.currentPageOffsetFraction}," +
                "$scrollPosition")
        val wormOffset = (scrollPosition%1)*2

        val xPos = scrollPosition.toInt() * distance

        val head = xPos+distance*0f.coerceAtLeast(wormOffset-1)
        val tail = xPos+size.width + 1f.coerceAtMost(wormOffset)*distance
        Log.d(Tag, "wormTransition() called " +
                "${pagerState.currentPage}," +
                "${pagerState.currentPageOffsetFraction}," +
                "$scrollPosition," +
                "$wormOffset," +
                "$xPos," +
                "$head," +
                "$tail",
        )
        val worm = RoundRect(head,0f,tail,size.height, CornerRadius(50f))
        val path = Path().apply {
            addRoundRect(worm)
        }
        drawPath(path = path, color = Green80)
    }
}