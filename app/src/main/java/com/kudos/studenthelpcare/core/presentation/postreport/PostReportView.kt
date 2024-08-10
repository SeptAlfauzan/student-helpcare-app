package com.kudos.studenthelpcare.core.presentation.postreport

import android.app.Activity
import android.util.Log
import android.view.WindowManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SmsFailed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.helper.Routes
import com.kudos.studenthelpcare.core.presentation.ComplaintsViewModel
import com.kudos.studenthelpcare.core.presentation.profile.ProfileViewModel
import com.kudos.studenthelpcare.core.presentation.widgets.ErrorHandler
import com.kudos.studenthelpcare.core.presentation.widgets.MyAlertDialog
import com.kudos.studenthelpcare.core.presentation.widgets.StatusBar
import com.kudos.studenthelpcare.core.utils.ResultState
import com.kudos.studenthelpcare.ui.theme.Rounded12
import com.kudos.studenthelpcare.ui.theme.StudentHelpcareTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostReportView(
    profileViewModel: ProfileViewModel,
    complaintsViewModel: ComplaintsViewModel, navigator: NavHostController
) {
    var report by remember {
        mutableStateOf("")
    }
    val interactionSource = remember { MutableInteractionSource() }
    val postComplaintState = complaintsViewModel.postComplaint.collectAsState().value
    val profileState = profileViewModel.profileState.collectAsState().value
    postComplaintState.let {
        when (it) {
            is ResultState.Fail -> {
                Log.d("TAG", "PostReportView: error")
                MyAlertDialog(
                    isShow = true,
                    onDismissRequest = { navigator.popBackStack() },
                    onConfirmation = { navigator.popBackStack() },
                    dialogTitle = "Error posting complaint!",
                    dialogText = it.error?.message
                        ?: "Error please check your connection",
                    icon = Icons.Default.SmsFailed
                )
            }

            is ResultState.Success -> MyAlertDialog(
                isShow = true,
                onDismissRequest = {
                    complaintsViewModel.resetPostComplaintState()
                    complaintsViewModel.getMyComplaints()
                    navigator.navigate(Routes.Home.route) {
                        popUpTo(navigator.graph.id) {
                            inclusive = false
                        }
                    }
                },
                onConfirmation = {
                    complaintsViewModel.resetPostComplaintState()
                    navigator.navigate(Routes.Home.route) {
                        popUpTo(navigator.graph.id) {
                            inclusive = false
                        }
                    }
                },
                dialogTitle = "Success posting new complaint!",
                dialogText = "Success posting new complaint, please check on dashboard",
                icon = Icons.Default.Check
            )

            else -> null
        }
    }

    Scaffold(containerColor = Color(0xFF1F2E38), topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
            title = { },
            navigationIcon = {
                IconButton(onClick = { navigator.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            actions = {
                Button(
                    onClick = { complaintsViewModel.postComplaint(report) },
                    enabled = report.isNotEmpty() && postComplaintState != ResultState.Loading,
                    border = BorderStroke(width = 1.dp, color = Color(0xFF547288)),
                    shape = Rounded12,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    if (postComplaintState == ResultState.Loading) CircularProgressIndicator(
                        modifier = Modifier.size(24.dp)
                    ) else Text(
                        text = stringResource(R.string.send),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            })
    }) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues = padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            //profile image
            profileState.let {
                when (it) {
                    ResultState.Empty -> profileViewModel.getProfile()
                    is ResultState.Fail -> ErrorHandler(error = it.error,
                        onRefresh = { profileViewModel.getProfile() })

                    ResultState.Loading -> CircularProgressIndicator(
                    )
                    is ResultState.Success -> AsyncImage(
                        model = it.data.data?.photoUrl ?: "-",
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(
                                CircleShape
                            )
                    )
                }
            }
            BasicTextField(
                cursorBrush = SolidColor(Color.White),
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                value = report, onValueChange = { report = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                interactionSource = interactionSource,
            ) {
                TextFieldDefaults.DecorationBox(
                    value = report,
                    contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                        0.dp
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    enabled = true,
                    innerTextField = it,
                    interactionSource = interactionSource,
                    singleLine = false,
                    visualTransformation = VisualTransformation.None,
                    placeholder = {
                        Text(
                            text = "Apa yang terjadi hari ini?",
                            color = MaterialTheme.colorScheme.onPrimary.copy(0.4f)
                        )
                    },
                )
            }
        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    StudentHelpcareTheme {
        Surface {
//            PostReportView(rememberNavController())
        }
    }
}