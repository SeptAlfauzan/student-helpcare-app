package com.kudos.studenthelpcare.core.presentation.profile

import android.content.Context
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.helper.Routes
import com.kudos.studenthelpcare.core.presentation.signin.SignInViewModel
import com.kudos.studenthelpcare.core.presentation.widgets.ErrorHandler
import com.kudos.studenthelpcare.core.utils.ResultState

@Composable
fun ProfileView(
    profileViewModel: ProfileViewModel,
    signInViewModel: SignInViewModel,
    navHostController: NavHostController,
) {

    val context: Context = LocalContext.current
    profileViewModel.profileState.collectAsState().value.let {
        when (it) {
            ResultState.Empty -> profileViewModel.getProfile()
            is ResultState.Fail -> ErrorHandler(error = it.error,
                onRefresh = { profileViewModel.getProfile() })

            ResultState.Loading -> Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is ResultState.Success -> Box(Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.pattern),
                    contentDescription = stringResource(R.string.pattern_bg),
                )

                Image(
                    painter = painterResource(id = R.drawable.bhinneka),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .zIndex(2f),
                    contentDescription = null,
                )

                Column(
                    Modifier.fillMaxSize()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        AsyncImage(
                            model = it.data.data?.photoUrl,
                            contentDescription = "profile-img",
                            modifier = Modifier
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
                            Text(text = it.data.data?.schoolName ?: "-", color = Color.White)
                        }
                    }

                    Column(
                        Modifier
                            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                            .background(Color.White)
                            .padding(24.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = it.data.data?.name ?: "-",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                        Text(
                            text = it.data.data?.email ?: "-",
                            fontWeight = FontWeight.Light,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(bottom = 32.dp)
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Text(
                                text = stringResource(R.string.others_menu),
                                color = Color(0xFF547288)
                            )
                            TileMenuItem(text = stringResource(R.string.bullying_menu), action = {
                                navHostController.navigate(Routes.BullyingMaterial.route)
                            })
                            TileMenuItem(text = stringResource(R.string.about_us), action ={
                                navHostController.navigate(Routes.AboutUs.route)
                            })
                            TileMenuItem(text = stringResource(R.string.app_guide_menu), action = {
                                navHostController.navigate(Routes.AppGuide.route)
                            })
                            TileMenuItem(text = stringResource(R.string.reset_password_menu),
                                action = {
                                    navHostController.navigate(Routes.ChangePassword.route)
                                })
                            TileMenuItem(text = stringResource(R.string.signout_menu), action = {
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
    }
}

@Composable
fun TileMenuItem(text: String, action: () -> Unit) {
    Row(modifier = Modifier
        .clickable { action() }
        .padding(vertical = 14.dp, horizontal = 24.dp)
        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = text)
        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
    }
}