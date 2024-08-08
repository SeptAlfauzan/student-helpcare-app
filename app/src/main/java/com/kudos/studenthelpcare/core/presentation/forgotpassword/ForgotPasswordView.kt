package com.kudos.studenthelpcare.core.presentation.forgotpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.presentation.widgets.TextInput

@Composable
fun ForgotPasswordView(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    var isSuccessfullySendEmail by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.pattern),
            contentDescription = "pattern bg",
modifier = Modifier.drawWithCache {
            onDrawWithContent {
                drawContent()
                drawRect(
                    Brush.verticalGradient(
                    0f to Color.White,
                    0.6f to Color.Transparent,
                ))
            }
        } )
        if (!isSuccessfullySendEmail) InputTemplateLayout(
            // TODO: CHANGE THIS PLZ
            onSendEmail = { isSuccessfullySendEmail = true },
            Modifier.align(Alignment.Center)
        ) else SuccessSendTemplateLayout(
            onNavBack = { navHostController.popBackStack() },
            Modifier.align(
                Alignment.Center
            )
        )
    }
}

@Composable
private fun InputTemplateLayout(
    onSendEmail: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember {
        mutableStateOf("")
    }
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
            Text(text = "Ubah password", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = "Masukkan email akun anda")
            TextInput(
                value = email,
                label = "Email",
                onChange = { email = it },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = { onSendEmail() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text(text = "Kirim email ganti password")
        }
    }
}

@Composable
private fun SuccessSendTemplateLayout(
    onNavBack: () -> Unit,
    modifier: Modifier = Modifier
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
            Text(text = "Email telah dikirim", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = "Email telah dikirim")
        }
        Button(
            onClick = { onNavBack() }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text(text = "Kembali")
        }
    }
}