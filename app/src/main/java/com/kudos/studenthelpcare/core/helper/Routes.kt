package com.kudos.studenthelpcare.core.helper

sealed class Routes(val route: String) {
    object Home: Routes(route = "/home")
    object Signin: Routes(route = "/signin")
    object Signup: Routes(route = "/signup")
    object ChangePassword: Routes(route = "/change-password")
    object Profile: Routes(route = "/change-password")
    object CreatePost: Routes(route = "/create-post")
}