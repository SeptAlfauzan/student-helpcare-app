package com.kudos.studenthelpcare.core.presentation.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kudos.studenthelpcare.R
import com.kudos.studenthelpcare.ui.theme.Rounded12


@Composable
fun BlueLogo(modifier: Modifier = Modifier) {
    Box(
        modifier
            .clip(Rounded12)
            .background(MaterialTheme.colorScheme.primary)
            .padding(22.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo__white),
            modifier = Modifier.size(48.dp),
            contentDescription = "logo"
        )
    }
}
