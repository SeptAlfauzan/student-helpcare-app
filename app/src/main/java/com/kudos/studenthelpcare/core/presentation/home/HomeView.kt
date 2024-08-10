package com.kudos.studenthelpcare.core.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.domain.entities.Comment
import com.kudos.studenthelpcare.core.domain.entities.Complaint
import com.kudos.studenthelpcare.core.domain.entities.UserProfile
import com.kudos.studenthelpcare.core.helper.Routes
import com.kudos.studenthelpcare.core.presentation.ComplaintsViewModel
import com.kudos.studenthelpcare.core.presentation.profile.ProfileViewModel
import com.kudos.studenthelpcare.core.presentation.widgets.CardReport
import com.kudos.studenthelpcare.core.presentation.widgets.ErrorHandler
import com.kudos.studenthelpcare.core.presentation.widgets.PulltoRefreshLazyColumn
import com.kudos.studenthelpcare.core.utils.ResultState
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    profileViewModel: ProfileViewModel,
    navHostController: NavHostController,
    complaintsViewModel: ComplaintsViewModel
) {
    val profileState = profileViewModel.profileState.collectAsState().value
    val avatarModifier = Modifier.padding(bottom = 8.dp)
    Column(
        Modifier
            .padding(top = 20.dp)
            .padding(horizontal = 24.dp)
    ) {

        profileState.let {
            when (it) {
                ResultState.Empty -> profileViewModel.getProfile()
                is ResultState.Fail -> ErrorHandler(error = it.error,
                    onRefresh = { profileViewModel.getProfile() })

                ResultState.Loading -> CircularProgressIndicator(
                    modifier = avatarModifier.align(Alignment.End)
                )

                is ResultState.Success -> AsyncImage(model = it.data.data?.photoUrl ?: "-",
                    contentDescription = null,
                    modifier = avatarModifier
                        .align(Alignment.End)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .size(36.dp)
                        .clickable { navHostController.navigate(Routes.Profile.route) })
            }
        }
        Text(
            text = stringResource(R.string.your_report),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
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
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }

                is ResultState.Success -> {
                    PulltoRefreshLazyColumn(items = it.data,
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
                        })
                }
            }
        }
    }
}

val reportData = listOf(
    Complaint(
        id = "",
        title = "title",
        desc = "desc",
        imageUrl = null,
        isResponded = false,
        comments = listOf(
            Comment(
                id = "",

                text = "Terimakasih atas laporannya, akan segera kami proses. Harap ditunggu ya \uD83D\uDC4D",
                created = LocalDate.now().toString(),
                userProfile = UserProfile(
                    id = "",
                    name = "Linda",
                    imageUrl = "",
                    schoolId = "Guru SMA 1 Callifornia",
                    username = ""
                )
            )
        )
    )
)