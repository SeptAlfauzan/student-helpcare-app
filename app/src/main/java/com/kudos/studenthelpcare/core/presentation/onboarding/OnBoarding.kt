package com.kudos.studenthelpcare.core.presentation.onboarding

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.domain.OnBoardingData
import com.kudos.studenthelpcare.ui.theme.StudentHelpcareTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoarding(
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(pageCount = {
        onBoardingDatas.size
    })
    Column(modifier.padding(24.dp).padding(top=148.dp)) {
        Column( modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
            HorizontalPager(state = pagerState, modifier = Modifier.weight(3f)) { page ->
                OnBoardingContent(data = onBoardingDatas[page])
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.weight(1f).align(Alignment.CenterHorizontally)) {
                for (i in 0 until onBoardingDatas.size){
                    Box(modifier = Modifier
                        .size(16.dp)
                        .clip(
                            CircleShape
                        )
                        .background(
                            if(i == pagerState.currentPage)
                            MaterialTheme.colorScheme.primary else Color.Gray)
                        .clickable { coroutineScope.launch {
                            pagerState.animateScrollToPage(i)
                        } }
                    )
                }
            }
        }

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
}

@Composable
fun OnBoardingContent(
    data: OnBoardingData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = data.imageId), contentDescription = data.imageDesc)
        Text(
            text = data.text,
            modifier = Modifier.padding(top = 16.dp),
            textAlign = TextAlign.Center
        )
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