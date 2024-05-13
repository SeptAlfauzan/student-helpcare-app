package com.kudos.studenthelpcare.core.presentation.onboarding.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.presentation.onboarding.onBoardingDatas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ButtonNavigations(
    pagerState: PagerState,
    coroutineScope: CoroutineScope
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (pagerState.currentPage > 0) Arrangement.SpaceBetween else Arrangement.End
    ) {
        if (pagerState.currentPage > 0) {
            Button(onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            }) {
                Text(text = stringResource(R.string.before))
            }
        }
        Button(onClick = {
            if (pagerState.currentPage == onBoardingDatas.size - 1) {

            } else {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        }) {
            Text(
                text = if (pagerState.currentPage == onBoardingDatas.size - 1) stringResource(R.string.finish) else stringResource(
                    R.string.next
                )
            )
        }
    }
}