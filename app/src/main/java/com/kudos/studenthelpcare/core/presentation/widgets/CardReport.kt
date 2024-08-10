package com.kudos.studenthelpcare.core.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.domain.entities.Comment
import com.kudos.studenthelpcare.core.domain.entities.Complaint
import com.kudos.studenthelpcare.core.domain.entities.UserProfile
import com.kudos.studenthelpcare.ui.theme.StudentHelpcareTheme
import java.time.LocalDate


@Composable
fun CardReport(data: Complaint) {
    Column(modifier = Modifier.padding(16.dp)) {
        Column(Modifier.fillMaxWidth()) {
            Text(text = data.title, fontWeight = FontWeight.Bold)
            Text(
                text = data.desc,
                color = Color(0xFF547288),
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 12.sp
            )
            Row(
                Modifier
                    .padding(top = 24.dp, bottom = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.Comment,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                )
                StatusIndicator(
                    isResponded = data.isResponded, modifier = Modifier
                )
            }
        }

        if (data.comments.isNotEmpty()) {
            Text(
                text = stringResource(R.string.comment),
                modifier = Modifier.padding(vertical = 12.dp),
                color = Color(0xFF547288)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = "https://ui-avatars.com/api/?name=Admin",
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 24.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .size(36.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Admin", color = Color(0xFF547288))
                    Text(text = data.comments[0].text)
                }
            }
        }else{
            Text(text = "Belum ada balasan", fontWeight = FontWeight.Light)
        }
    }
}

@Composable
fun StatusIndicator(isResponded: Boolean, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(
                RoundedCornerShape(8.dp)
            )
            .background(if (isResponded) Color.Green else Color.Yellow)
            .padding(6.dp)
    ) {
        Icon(
            imageVector = if (isResponded) Icons.Default.CheckCircle else Icons.Default.HourglassTop,
            contentDescription = null,
            tint = if (isResponded) Color.White else Color.Black
        )
        Text(
            text = if (isResponded) "diterima" else "diproses",
            color = if (isResponded) Color.White else Color.Black
        )
    }
}


@Preview
@Composable
private fun CardReportPreview() {
    StudentHelpcareTheme {
        Surface {
            CardReport(
                data =
                Complaint(
                    id = "",
                    title = "Laporan pencurian",
                    desc = "Terjadi sekitar pukul 16.00 di kelas D",
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
        }
    }
}