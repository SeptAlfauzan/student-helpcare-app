package com.kudos.studenthelpcare.core.presentation.postreport

import android.app.Activity
import android.view.WindowManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.presentation.widgets.StatusBar
import com.kudos.studenthelpcare.ui.theme.Rounded12
import com.kudos.studenthelpcare.ui.theme.StudentHelpcareTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostReportView(navigator: NavHostController) {
    var report by remember {
        mutableStateOf("")
    }
    val interactionSource = remember { MutableInteractionSource() }
    Scaffold(
        containerColor = Color(0xFF1F2E38),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                title = { }, navigationIcon = {
                    IconButton(onClick = { navigator.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }, actions = {
                    Button(
                        onClick = { /*TODO*/ },
                        enabled = report.isNotEmpty(),
                        border = BorderStroke(width = 1.dp, color = Color(0xFF547288)),
                        shape = Rounded12,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Text(
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
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clip(
                        CircleShape
                    )
            )
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
            PostReportView(rememberNavController())
        }
    }
}