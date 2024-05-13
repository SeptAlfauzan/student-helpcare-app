package com.kudos.studenthelpcare.core.presentation.onboarding.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kudos.studenthelpcare.core.presentation.onboarding.onBoardingDatas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Indicators(
    coroutineScope: CoroutineScope,
    pagerState: PagerState, modifier: Modifier = Modifier
){
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier) {
        for (i in onBoardingDatas.indices){
            Box(modifier = Modifier
                .size(16.dp)
                .clip(
                    CircleShape
                )
                .background(
                    if (i == pagerState.currentPage)
                        MaterialTheme.colorScheme.primary else Color(0xFFF4F4F4)
                )
                .clickable {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(i)
                    }
                }
            )
        }
    }
}
