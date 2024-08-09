package com.kudos.studenthelpcare.core.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kudos.studenthelpcare.ui.theme.Rounded12

@Composable
fun TextInput(
    value: String,
    label: String,
    onChange: (String) -> Unit,
    isSecure: Boolean = false,
    modifier: Modifier = Modifier
) {
    var peekPassword by remember {
        mutableStateOf(!isSecure)
    }
    OutlinedTextFieldBackground(color = Color.White) {

        OutlinedTextField(
            shape = Rounded12,
            label = {
                Text(text = label)
            },

            visualTransformation = if (!peekPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = if (isSecure) KeyboardType.Password else KeyboardType.Text),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF547288),
                unfocusedBorderColor = Color(0xFF547288),
            ),

            trailingIcon = {
                if (isSecure) IconButton(onClick = { peekPassword = !peekPassword }) {
                    Icon(
                        imageVector = if (!peekPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            value = value,
            onValueChange = onChange,
            modifier = modifier
        )
    }
}