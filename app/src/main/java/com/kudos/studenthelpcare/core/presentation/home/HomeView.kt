package com.kudos.studenthelpcare.core.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.data.source.remote.response.UserProfileResponse
import com.kudos.studenthelpcare.core.helper.Routes
import com.kudos.studenthelpcare.core.presentation.ComplaintsViewModel
import com.kudos.studenthelpcare.core.presentation.profile.ProfileViewModel
import com.kudos.studenthelpcare.core.presentation.widgets.CardReport
import com.kudos.studenthelpcare.core.presentation.widgets.ErrorHandler
import com.kudos.studenthelpcare.core.presentation.widgets.PulltoRefreshLazyColumn
import com.kudos.studenthelpcare.core.utils.ResultState

@Composable
fun HomeView(
    profileViewModel: ProfileViewModel,
    navHostController: NavHostController,
    complaintsViewModel: ComplaintsViewModel
) {
    val profileState = profileViewModel.profileState.collectAsState().value
    val avatarModifier = Modifier.padding(bottom = 8.dp)
    LaunchedEffect(Unit) {
        complaintsViewModel.getMyComplaints()
    }
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .zIndex(2f)
                .padding(top = 20.dp)
                .padding(horizontal = 24.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .zIndex(2f)
            ) {
                Box(modifier = Modifier.align(Alignment.TopEnd)) {
                    ProfilePic(
                        profileState = profileState,
                        getProfile = { profileViewModel.getProfile() },
                        navHostController = navHostController,
                        modifier = avatarModifier.align(Alignment.CenterEnd)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.police),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Column(modifier = Modifier.fillMaxWidth().clip(shape = RoundedCornerShape(8)).background(Color.White)
                .weight(1f)) {
                Text(
                    modifier = Modifier.background(Color.White).padding(12.dp).zIndex(2f).fillMaxWidth(),
                    text = stringResource(R.string.your_report),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
                complaintsViewModel.complaints.collectAsState().value.let {
                        when (it) {
                            ResultState.Empty -> complaintsViewModel.getMyComplaints()
                            is ResultState.Fail -> Column {
                                Text(text = it.error?.message ?: "error fetch")
                                Button(onClick = { complaintsViewModel.getMyComplaints() }) {
                                    Text(text = "Retry")
                                }
                            }

                            ResultState.Loading -> Column(
                                Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                            }

                            is ResultState.Success -> {
                                PulltoRefreshLazyColumn(
                                    paddingValues = PaddingValues(12.dp),
                                    items = it.data,
                                    content = {
                                        Card {
                                            CardReport(
                                                data = it
                                            )
                                        }
                                    },
                                    isRefreshing = complaintsViewModel.complaints.collectAsState().value == ResultState.Loading,
                                    onRefresh = {
                                        complaintsViewModel.getMyComplaints()
                                    },
                                )
                            }
                        }
                }

            }
        Image(
            painter = painterResource(id = R.drawable.anti_bullying),
            contentDescription = null,
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )
        }

    }
}


@Composable
private fun ProfilePic(
    profileState: ResultState<UserProfileResponse>,
    getProfile: () -> Unit,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {

    LaunchedEffect(Unit) {
        getProfile()
    }

    profileState.let {
        when (it) {
            ResultState.Empty -> getProfile()
            is ResultState.Fail -> ErrorHandler(error = it.error, onRefresh = { getProfile() })

            ResultState.Loading -> CircularProgressIndicator(
                modifier = modifier
            )

            is ResultState.Success -> AsyncImage(model = it.data.data?.photoUrl ?: "-",
                contentDescription = null,
                modifier = modifier
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .size(36.dp)
                    .clickable { navHostController.navigate(Routes.Profile.route) })
        }
    }
}