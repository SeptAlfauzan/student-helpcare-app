package com.kudos.studenthelpcare.core.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.domain.entities.Comment
import com.kudos.studenthelpcare.core.domain.entities.Report
import com.kudos.studenthelpcare.core.domain.entities.UserProfile
import com.kudos.studenthelpcare.core.presentation.widgets.CardReport
import com.kudos.studenthelpcare.core.presentation.widgets.StatusBar
import java.time.LocalDate

@Composable
fun HomeView(){
    Column(
        Modifier
            .padding(top = 20.dp)
            .padding(horizontal = 24.dp)) {
        AsyncImage(
            model = "",
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.End)
                .clip(CircleShape)
                .background(Color.Gray)
                .size(36.dp)
        )
        Text(text = stringResource(R.string.your_report), modifier = Modifier.padding(bottom = 38.dp), fontWeight = FontWeight.Bold)
        LazyColumn {
            items(5) {
                Card(modifier = Modifier.padding(bottom = 12.dp)) {
                CardReport(
                    data = Report(
                        id = "",
                        title = "Laporan pencurian",
                        desc = "Terjadi sekitar pukul 16.00 di kelas D",
                        imageUrl = null,
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
                }
            }
        }
    }
}