package com.kudos.studenthelpcare.core.presentation.forgotpassword

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.SmsFailed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.presentation.widgets.MyAlertDialog
import com.kudos.studenthelpcare.core.presentation.widgets.TextInput
import com.kudos.studenthelpcare.core.utils.ResultState
import com.kudos.studenthelpcare.core.utils.validateEmailValid
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ForgotPasswordView(
    navHostController: NavHostController,
    forgotPasswordViewModel: ForgotPasswordViewModel,
    modifier: Modifier = Modifier,
) {
    var isSuccessfullySendEmail by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        isSuccessfullySendEmail = false
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.pattern),
            contentDescription = stringResource(R.string.pattern_bg),
            modifier = Modifier.drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRect(
                        Brush.verticalGradient(
                            0f to Color.White,
                            0.6f to Color.Transparent,
                        )
                    )
                }
            })
        if (!isSuccessfullySendEmail) InputTemplateLayout(
            onSendEmail = { forgotPasswordViewModel.sendResetPasswordToEmail(email = it) },
            onSuccess = { isSuccessfullySendEmail = true },
            state = forgotPasswordViewModel.state,
            Modifier.align(Alignment.Center)
        ) else SuccessSendTemplateLayout(
            onNavBack = {
                navHostController.popBackStack()
            }, Modifier.align(
                Alignment.Center
            )
        )
    }
}

@Composable
private fun InputTemplateLayout(
    onSendEmail: (String) -> Unit,
    onSuccess: () -> Unit,
    state: StateFlow<ResultState<String>>, modifier: Modifier = Modifier,
) {
    var email by remember {
        mutableStateOf("")
    }
    var loading by remember {
        mutableStateOf(false)
    }

    state.collectAsState().value.let {
        when (it) {
            ResultState.Empty -> loading = false
            is ResultState.Fail -> {
                MyAlertDialog(onDismissRequest = {  },
                    onConfirmation = {  },
                    dialogTitle = stringResource(R.string.fail_to_reset_password),
                    dialogText = it.error?.message ?: stringResource(R.string.error_send_email_default_text),
                    icon = Icons.Default.SmsFailed
                )
                loading = false
            }

            ResultState.Loading -> loading = true
            is ResultState.Success -> {
                onSuccess()
                loading = false
            }
        }
    }
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.change_password), fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = stringResource(R.string.insert_email))
            TextInput(
                value = email,
                label = stringResource(id = R.string.email),
                onChange = { email = it },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            enabled = email.isNotEmpty() && email.validateEmailValid(),
            onClick = { onSendEmail(email) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            if (loading) CircularProgressIndicator(
                color = Color.White, modifier = Modifier.size(24.dp)
            ) else Text(text = stringResource(R.string.send_email_change_password))

        }
    }
}

@Composable
private fun SuccessSendTemplateLayout(
    onNavBack: () -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier
            .padding(vertical = 144.dp)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.email_sent), fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = stringResource(R.string.email_sent))
        }
        Button(
            onClick = { onNavBack() }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text(text = stringResource(R.string.back))
        }
    }
}