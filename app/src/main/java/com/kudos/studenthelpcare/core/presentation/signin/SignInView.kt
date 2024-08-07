package com.kudos.studenthelpcare.core.presentation.signin

import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SmsFailed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.helper.Routes
import com.kudos.studenthelpcare.core.presentation.widgets.BlueLogo
import com.kudos.studenthelpcare.core.presentation.widgets.MyAlertDialog
import com.kudos.studenthelpcare.core.presentation.widgets.TextInput
import com.kudos.studenthelpcare.core.utils.ResultState
import com.kudos.studenthelpcare.ui.theme.Rounded12
import com.kudos.studenthelpcare.ui.theme.StudentHelpcareTheme

@Composable
fun SignInView(signInViewModel: SignInViewModel, navHostController: NavHostController) {
    var password by remember {
        mutableStateOf("")
    }
    var username by remember {
        mutableStateOf("")
    }
    var errorSignin by remember {
        mutableStateOf("")
    }

    signInViewModel.signinResult.collectAsState().value.let {
        when (it) {
            is ResultState.Fail -> {
                errorSignin = it.error?.message ?: "Error unexpected"
                MyAlertDialog(
                    isShow = true,
                    onDismissRequest = { signInViewModel.resetSigninResult() },
                    onConfirmation = { signInViewModel.resetSigninResult() },
                    dialogTitle = "Login Gagal!",
                    dialogText = "Silahkan cek username dan password anda!\n$errorSignin",
                    icon = Icons.Default.SmsFailed
                )
            }

            ResultState.Loading -> {}
            ResultState.Empty -> {}
            is ResultState.Success -> {
                navHostController.navigate(Routes.Home.route)
                signInViewModel.resetSigninResult()
            }
        }
    }


    Scaffold { padding ->
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.pattern),
                contentDescription = "pattern bg"
            )
            Column(
                Modifier
                    .padding(padding)
                    .padding(24.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                BlueLogo(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 84.dp)
                )

                SpannableTextScreen(
                    navigateCreateAccount = { navHostController.navigate(Routes.Signup.route) },
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                )
//                Button(
//                    onClick = { /*TODO*/ },
//                    border = BorderStroke(width = 1.dp, color = Color(0xFF547288)),
//                    shape = Rounded12,
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = MaterialTheme.colorScheme.surface
//                    )
//                ) {
//                    Image(
//                        modifier = Modifier.size(36.dp),
//                        painter = painterResource(id = R.drawable.google_ic),
//                        contentDescription = null
//                    )
//                }
//                Text(text = "atau", modifier = Modifier.padding(vertical = 24.dp))
                TextInput(
                    value = username,
                    onChange = { username = it },
                    label = stringResource(R.string.username),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                TextInput(
                    value = password,
                    onChange = { password = it },
                    isSecure = true,
                    label = "Password",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "lupa password?")
                }
                Button(
                    shape = Rounded12,
                    onClick = {
                        signInViewModel.signin(username, password)
                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    if (signInViewModel.signinResult.collectAsState().value is ResultState.Loading) CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(20.dp)
                    ) else Text(
                        text = stringResource(R.string.signin)
                    )
                }
            }
        }
    }
}

@Composable
fun SpannableTextScreen(
    navigateCreateAccount: () -> Unit = {}, modifier: Modifier = Modifier
) {
    val text = buildAnnotatedString {
        append("Belum punya akun? ")
        pushStringAnnotation(tag = "click", annotation = "click")
        withStyle(
            SpanStyle(
                color = MaterialTheme.colorScheme.primary
            )
        ) {
            append("Buat akun ")
        }
        pop()
    }
    ClickableText(text = text, modifier, onClick = { offset ->
        text.getStringAnnotations(tag = "click", start = offset, end = offset).firstOrNull()?.let {
            navigateCreateAccount()
        }
    })
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    StudentHelpcareTheme {
        Surface {
//            SignInView()
        }
    }
}