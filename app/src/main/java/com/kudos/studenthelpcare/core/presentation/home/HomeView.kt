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
import androidx.compose.material3.Text
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
import com.kudos.studenthelpcare.core.presentation.widgets.CardReport
import com.kudos.studenthelpcare.core.utils.ResultState
import java.time.LocalDate

@Composable
fun HomeView(
    navHostController: NavHostController, complaintsViewModel: ComplaintsViewModel
) {

    Column(
        Modifier
            .padding(top = 20.dp)
            .padding(horizontal = 24.dp)
    ) {
        AsyncImage(model = "",
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.End)
                .clip(CircleShape)
                .background(Color.Gray)
                .size(36.dp)
                .clickable { navHostController.navigate(Routes.Profile.route) })
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
                ResultState.Loading -> Column(Modifier.fillMaxWidth().weight(1f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    CircularProgressIndicator()
                }
                is ResultState.Success -> {
                    LazyColumn(contentPadding = PaddingValues(top = 18.dp)) {
                        if (reportData.isEmpty()) {
                            item {
                                Text(
                                    text = "Kamu belum membuat laporan, klik tombol dibawah untuk memnuat laporan baru",
                                    fontWeight = FontWeight.Light,
                                    textAlign = TextAlign.Center
                                )
                            }
                        } else {
                            items(it.data) {
                                Card(modifier = Modifier.padding(bottom = 12.dp)) {
                                    CardReport(
                                        data = it
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

val reportData = listOf(
    Complaint(
        id = "", title = "title", desc = "desc", imageUrl = null,
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