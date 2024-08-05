package com.kudos.studenthelpcare

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kudos.studenthelpcare.core.StudentHelpcareApp
import com.kudos.studenthelpcare.core.data.repositories.SchoolRepositoriesImpl
import com.kudos.studenthelpcare.core.helper.Routes
import com.kudos.studenthelpcare.core.presentation.SchoolViewModel
import com.kudos.studenthelpcare.ui.theme.StudentHelpcareTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val schoolViewModel: SchoolViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController: NavHostController = rememberNavController()

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            StudentHelpcareTheme(
                darkTheme = false,
                statusBarPrimary = currentRoute == Routes.CreatePost.route
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column {
                        StudentHelpcareApp(navController, currentRoute = currentRoute)
                    }
                }
            }
        }
    }
}