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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.data.Comment
import com.kudos.studenthelpcare.core.data.Report
import com.kudos.studenthelpcare.core.data.UserProfile
import com.kudos.studenthelpcare.ui.theme.StudentHelpcareTheme
import java.time.LocalDate
import java.util.Date


@Composable
fun CardReport(data: Report) {
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(text = data.title, fontWeight = FontWeight.Bold)
                Text(text = data.desc, color = Color(0xFF547288))
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    modifier = Modifier.rotate(90f)
                )
            }
        }
        Box(
            Modifier
                .padding(top = 24.dp, bottom = 24.dp)
                .fillMaxWidth()
        ) {
           if(data.imageUrl != null) AsyncImage(
                model = data.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Gray)
                    .height(208.dp)
            )
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.BottomStart)
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = null,
                    tint =if(data.imageUrl != null) Color.White else Color(0xFF0161A7)
                )
            }
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.BottomEnd)
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
                    .background(Color(0xFF63FFA1))
                    .padding(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color.White
                )
                Text(text = "diterima", color = Color.White)
            }
        }
        if(data.comments.isNotEmpty()){
            Text(text = stringResource(R.string.comment), modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFF547288))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = data.comments[0].userProfile.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 24.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .size(64.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = data.comments[0].userProfile.name, color = Color(0xFF547288), fontWeight = FontWeight.Bold)
                        Text(text = data.comments[0].created, color = Color(0xFF547288))
                    }
                    Text(text =data.comments[0].userProfile.schoolId, color = Color(0xFF547288))
                    Text(text = data.comments[0].text)
                }
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = stringResource(R.string.see_more_comment))
            }
        }
    }
}

@Preview
@Composable
private fun CardReportPreview() {
    StudentHelpcareTheme {
        Surface {
            CardReport(
                data =
                Report(
                    id = "",
                    title = "Laporan pencurian",
                    desc = "Terjadi sekitar pukul 16.00 di kelas D",
                    imageUrl =null,
                    comments = listOf(
                        Comment(
                            id="",
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