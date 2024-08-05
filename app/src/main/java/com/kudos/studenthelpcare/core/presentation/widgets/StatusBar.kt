package com.kudos.studenthelpcare.core.presentation.widgets

import android.app.Activity
import android.view.WindowManager
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun StatusBar(isExpanded: Boolean){
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window

        if(isExpanded) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
            )
        }
        window.statusBarColor = if(isExpanded)  android.graphics.Color.TRANSPARENT else Color.White.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isExpanded
    }
}