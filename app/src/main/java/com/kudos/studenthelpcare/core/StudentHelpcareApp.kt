package com.kudos.studenthelpcare.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kudos.studenthelpcare.core.helper.Routes
import com.kudos.studenthelpcare.core.presentation.ComplaintsViewModel
import com.kudos.studenthelpcare.core.presentation.SchoolViewModel
import com.kudos.studenthelpcare.core.presentation.bullying.BullyingView
import com.kudos.studenthelpcare.core.presentation.forgotpassword.ForgotPasswordView
import com.kudos.studenthelpcare.core.presentation.forgotpassword.ForgotPasswordViewModel
import com.kudos.studenthelpcare.core.presentation.home.HomeView
import com.kudos.studenthelpcare.core.presentation.postreport.PostReportView
import com.kudos.studenthelpcare.core.presentation.profile.ProfileView
import com.kudos.studenthelpcare.core.presentation.signin.SignInView
import com.kudos.studenthelpcare.core.presentation.signin.SignInViewModel
import com.kudos.studenthelpcare.core.presentation.signup.SignUpView
import com.kudos.studenthelpcare.core.presentation.signup.SignupViewModel
import okhttp3.Route
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentHelpcareApp(
    navController: NavHostController,
    currentRoute: String?,
    signInViewModel: SignInViewModel,
    schoolViewModel: SchoolViewModel,
    signupViewModel: SignupViewModel,
    forgotPasswordViewModel: ForgotPasswordViewModel,
    complaintsViewModel: ComplaintsViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            if (currentRoute == Routes.ChangePassword.route || currentRoute == Routes.BullyingMaterial.route) TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ChevronLeft,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            if (currentRoute == Routes.Home.route) FloatingActionButton(onClick = {
                navController.navigate(
                    Routes.CreatePost.route
                )
            }, containerColor = MaterialTheme.colorScheme.primary) {
                Icon(imageVector = Icons.Default.Create, contentDescription = null)
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = signInViewModel.isLogged.collectAsState().value.let {
                when (it) {
                    true -> Routes.Home.route
                    false -> Routes.Signin.route
                }
            },
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Routes.Home.route) {
                HomeView(navHostController = navController, complaintsViewModel = complaintsViewModel)
            }
            composable(route = Routes.CreatePost.route) {
                PostReportView(navigator = navController)
            }
            composable(route = Routes.Signin.route) {
                SignInView(signInViewModel, navController)
            }
            composable(route = Routes.Signup.route) {
                SignUpView(
                    navController,
                    schoolViewModel = schoolViewModel,
                    signupViewModel = signupViewModel
                )
            }
            composable(route = Routes.Profile.route) {
                ProfileView(signInViewModel = signInViewModel, navHostController = navController)
            }
            composable(route = Routes.ChangePassword.route) {
                ForgotPasswordView(navController, forgotPasswordViewModel)
            }
            composable(route = Routes.BullyingMaterial.route) {
                BullyingView()
            }
        }
    }
}