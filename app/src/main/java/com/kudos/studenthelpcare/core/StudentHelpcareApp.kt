package com.kudos.studenthelpcare.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kudos.studenthelpcare.core.helper.Routes
import com.kudos.studenthelpcare.core.presentation.home.HomeView
import com.kudos.studenthelpcare.core.presentation.postreport.PostReportView
import com.kudos.studenthelpcare.core.presentation.signin.SignInView
import com.kudos.studenthelpcare.core.presentation.signin.SignInViewModel
import com.kudos.studenthelpcare.core.presentation.signup.SignUpView

@Composable
fun StudentHelpcareApp(
    navController: NavHostController,
    currentRoute: String?,
    signInViewModel: SignInViewModel,
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
        if(currentRoute == Routes.Home.route) FloatingActionButton(onClick = { navController.navigate(Routes.CreatePost.route) }, containerColor = MaterialTheme.colorScheme.primary) {
            Icon(imageVector = Icons.Default.Create, contentDescription = null)
        }
    }) {innerPadding ->
        NavHost(navController = navController, startDestination = Routes.Signin.route, modifier = Modifier.padding(innerPadding)){
            composable(route = Routes.Home.route){
                HomeView()
            }
            composable(route = Routes.CreatePost.route){
                PostReportView(navigator = navController)
            }
            composable(route = Routes.Signin.route){
                SignInView(signInViewModel, navController)
            }
            composable(route = Routes.Signup.route){
                SignUpView()
            }
        }
    }
}