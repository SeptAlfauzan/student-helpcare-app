package com.kudos.studenthelpcare.core.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kudos.studenthelpcare.R
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.VerticalPdfReaderState

@Composable
fun PdfReaderLib(modifier: Modifier = Modifier){
    val pdfState = VerticalPdfReaderState(
        resource = ResourceType.Asset(R.raw.app_guide),
        isZoomEnable = true
    )

    VerticalPDFReader(
        state = pdfState,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    )
}