package com.kudos.studenthelpcare.core.presentation.chatroom.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.core.presentation.widgets.TextInput

@Composable
fun MessageInputField(
    text: String,
    onChange: (String) -> Unit,
    onSend: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextInput(
            value = text,
            label = stringResource(R.string.insert_message),
            onChange = onChange,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = { if(text.isNotEmpty()) onSend() else null }, colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(
                    alpha = if (text.isNotEmpty()) 1f else 0.4f
                )
            )
        ) {
            Icon(imageVector = Icons.Default.Send, contentDescription = null, tint = Color.White)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    var message by remember { mutableStateOf("") }
    Surface {
        MessageInputField(
            text = message,
            onChange = { message = it },
            onSend = { print(message) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}