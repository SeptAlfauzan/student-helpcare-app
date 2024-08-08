package com.kudos.studenthelpcare.core.presentation.profile

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SmsFailed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.helper.Routes
import com.kudos.studenthelpcare.core.presentation.signin.SignInViewModel
import com.kudos.studenthelpcare.core.presentation.widgets.MyAlertDialog

@Composable
fun ProfileView(
    signInViewModel: SignInViewModel,
    navHostController: NavHostController,
) {

    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.pattern),
            contentDescription = "pattern bg",
        )
        Column(
            Modifier
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                AsyncImage(
                    model = "", contentDescription = "profile-img", modifier = Modifier
                        .size(72.dp)
                        .clip(
                            CircleShape
                        )
                        .background(Color.Gray)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(12.dp)
                        )
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(horizontal = 14.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(text = "SMA 1 Blablabla", color = Color.White)
                }
            }

            Column(
                Modifier
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color.White)
                    .padding(24.dp)
                    .weight(1f)
            ) {
                Text(text = "Lindy", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Text(
                    text = "@Lindy",
                    fontWeight = FontWeight.Light,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(text = "Lainnya", color = Color(0xFF547288))
                    TileMenuItem(text = "Reset password", action = {
                        navHostController.navigate(Routes.ChangePassword.route)
                    })
                    TileMenuItem(text = "Keluar", action = {
                        try {
                            signInViewModel.logout()
                            navHostController.navigate(Routes.Signin.route) {
                                popUpTo(navHostController.graph.id) {
                                    inclusive = false
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    })
                }
            }
        }

    }
}


@Composable
fun TileMenuItem(text: String, action: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { action() }
            .padding(vertical = 14.dp, horizontal = 24.dp)
            .fillMaxWidth()
        , horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text)
        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
    }
}