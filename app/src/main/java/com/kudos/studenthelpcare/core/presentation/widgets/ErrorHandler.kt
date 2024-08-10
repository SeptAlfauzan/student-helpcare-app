package com.kudos.studenthelpcare.core.presentation.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.xml.sax.ErrorHandler

@Composable
fun ErrorHandler(
    error: Throwable?,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = error?.message ?: "Error!")
        Button(onClick = { onRefresh() }) {
            Row {
                Text(text = "Refresh")
                Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            }
        }
    }
}