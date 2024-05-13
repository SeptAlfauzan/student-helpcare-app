package com.kudos.studenthelpcare.core.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.domain.OnBoardingData
import com.kudos.studenthelpcare.core.presentation.onboarding.widgets.ButtonNavigations
import com.kudos.studenthelpcare.core.presentation.onboarding.widgets.Indicators
import com.kudos.studenthelpcare.core.presentation.onboarding.widgets.OnBoardingContent
import com.kudos.studenthelpcare.ui.theme.StudentHelpcareTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoarding(
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(pageCount = {
        onBoardingDatas.size
    })
    Column(
        modifier
            .padding(24.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.logo), modifier = Modifier.size(48.dp).align(Alignment.CenterHorizontally),  contentDescription = null)
        Column(
            Modifier
                .padding(top = 148.dp)
        ) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                HorizontalPager(state = pagerState, modifier = Modifier.weight(3f)) { page ->
                    OnBoardingContent(data = onBoardingDatas[page])
                }
                Indicators(
                    coroutineScope = coroutineScope,
                    pagerState = pagerState,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterHorizontally)
                )
            }
            ButtonNavigations(pagerState = pagerState, coroutineScope = coroutineScope)
        }
    }
}


val onBoardingDatas = listOf(
    OnBoardingData(
        text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        imageId = R.drawable.onboard_0,
        imageDesc = null
    ),
    OnBoardingData(
        text = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
        imageId = R.drawable.onboard_1,
        imageDesc = null
    ),
    OnBoardingData(
        text = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.",
        imageId = R.drawable.onboard_2,
        imageDesc = null
    ),
)

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    StudentHelpcareTheme {
        Surface {
            OnBoarding()
        }
    }
}