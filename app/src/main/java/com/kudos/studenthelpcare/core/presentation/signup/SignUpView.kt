package com.kudos.studenthelpcare.core.presentation.signup

import android.util.Log
import android.widget.Toast


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.data.source.remote.response.SchoolDataItem
import com.kudos.studenthelpcare.core.domain.entities.SignupBody
import com.kudos.studenthelpcare.core.helper.Routes
import com.kudos.studenthelpcare.core.presentation.SchoolViewModel
import com.kudos.studenthelpcare.core.presentation.widgets.BlueLogo
import com.kudos.studenthelpcare.core.presentation.widgets.SearchableDropdown
import com.kudos.studenthelpcare.core.presentation.widgets.TextInput
import com.kudos.studenthelpcare.core.utils.ResultState
import com.kudos.studenthelpcare.ui.theme.Rounded12

@Composable
fun SignUpView(
    navHostController: NavHostController,
    schoolViewModel: SchoolViewModel,
    signupViewModel: SignupViewModel,
    modiifer: Modifier = Modifier
) {
    val context = LocalContext.current
    var password by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }

    var schoolItems: List<SchoolDataItem>? by remember {
        mutableStateOf(null)
    }

    var selectedSchoolId by remember {
        mutableStateOf("")
    }

    schoolViewModel.schools.collectAsState().value.let {
        when (it) {
            ResultState.Empty -> {
                schoolViewModel.getSchool()
                schoolItems = null
            }

            is ResultState.Fail -> {
                Toast.makeText(LocalContext.current, "Can't load school data", Toast.LENGTH_SHORT)
                    .show()
                schoolItems = null
            }

            ResultState.Loading -> {
                schoolItems = null
            }

            is ResultState.Success -> {
                schoolItems = it.data.data?.filterNotNull()
            }
        }
    }


    Scaffold { padding ->
        Box(
            Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.pattern),
                contentDescription = "pattern bg"
            )

            Column(
                Modifier
                    .padding(padding)
                    .padding(24.dp)

                    .verticalScroll(rememberScrollState())
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
                    navigate = {navHostController.navigate(Routes.Signin.route)}, modifier = Modifier.padding(bottom = 24.dp)
                )
                TextInput(
                    value = name,
                    onChange = { name = it },
                    label = stringResource(R.string.name),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                TextInput(
                    value = email,
                    onChange = { email = it },
                    label = stringResource(R.string.username),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                TextInput(
                    value = password,
                    onChange = { password = it },
                    isSecure = true,
                    label = stringResource(R.string.password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                SearchableDropdown(label = "School",
                    onValidSelect = {
                        Log.d("TAG", "SignUpView: $it")
                        selectedSchoolId = it
                    },
                    onInvalidSelect = {},
                    readonly = schoolItems != null,
                    items = schoolItems?.map { it.name!! }?.toList() ?: listOf(),
                    itemIds = schoolItems?.map { it.id!! }?.toList() ?: listOf(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                Button(
                    enabled = password.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty() && selectedSchoolId.isNotEmpty(),
                    shape = Rounded12,
                    onClick = {
                        val signupBody = SignupBody(
                            email = email,
                            password = password,
                            name = name,
                            idSchool = selectedSchoolId
                        )
                        signupViewModel.signup(signupBody = signupBody, onSuccess = {
                            navHostController.navigate(Routes.Signin.route) {
                                popUpTo(navHostController.graph.id) {
                                    inclusive = false
                                }
                            }
                        }, onFail = {
                            Toast.makeText(context, "Error: $it", Toast.LENGTH_SHORT).show()
                        })
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Daftar")
                }
            }

        }
    }
}

@Composable
fun SpannableTextScreen(
    navigate: () -> Unit = {}, modifier: Modifier = Modifier
) {
    val text = buildAnnotatedString {
        append("Sudah punya akun? ")
        pushStringAnnotation(tag = "click", annotation = "click")
        withStyle(
            SpanStyle(
                color = MaterialTheme.colorScheme.primary
            )
        ) {
            append("Masuk ke akun anda")
        }
        pop()
    }
    ClickableText(text = text, modifier, onClick = { offset ->
        text.getStringAnnotations(tag = "click", start = offset, end = offset).firstOrNull()?.let {
            navigate()
        }
    })
}

//@Preview(device = Devices.PIXEL_4)
//@Composable
//private fun Preview() {
//    StudentHelpcareTheme {
//        Surface {
//            SignUpView(rememberNavController())
//        }
//    }
//}