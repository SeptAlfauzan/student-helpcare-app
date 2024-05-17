package com.kudos.studenthelpcare.core.presentation.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kudos.studenthelpcare.core.data.UserProfile
import com.kudos.studenthelpcare.ui.theme.StudentHelpcareTheme
import java.time.LocalDate


data class Chat(
    val id: String,
    val userProfile: UserProfile,
    val created: String,
    val text: String,
    val isMe: Boolean
)
@Composable
fun ChatBubble(data: Chat){
    Box {
        Column(
            Modifier
                .fillMaxWidth(fraction = 1f)
                .drawBehind {
                    val trianglePath = Path().apply {
                        val height = size.height
                        val width = size.width
                        moveTo(0f, 0f)
                        lineTo(0f, height + 24f)
                        lineTo(24f, height)
                        lineTo(width, height)
                        lineTo(width, 0f)
                        lineTo(0f, 0f)
                        lineTo(0f, height)
                    }

                    val trianglePathVariant = Path().apply {
                        val height = size.height
                        val width = size.width
                        moveTo(width, 0f)
                        lineTo(width, height + 24f)
                        lineTo(width - 24f, height)
                        lineTo(0f, height)
                        lineTo(0f, 0f)
                        lineTo(width, 0f)
                        lineTo(width, height)
                    }


                    drawIntoCanvas { canvas ->
                        canvas.drawOutline(
                            outline = Outline.Generic(if(data.isMe) trianglePathVariant else trianglePath),
                            paint = Paint().apply {
                                color =
                                    Color(0xFF0161A7).copy(alpha = if (data.isMe) 0.35f else 0.1f)
                                pathEffect = PathEffect.cornerPathEffect(8.dp.toPx())
                            }
                        )
                    }
                }
        ) {
            Column( Modifier.fillMaxWidth().padding(24.dp)) {
                Text(
                    text = data.text, modifier = Modifier.align(
                        if (data.isMe) Alignment.End else Alignment.Start
                    )
                )
                Text(
                    text = data.created, modifier = Modifier.align(
                        if (!data.isMe) Alignment.End else Alignment.Start
                    ),
                    color = Color(0xFF547288)
                )
            }
        }

        Canvas(modifier = Modifier.fillMaxWidth()) {
            val trianglePath = Path().apply {
                val height = size.height
                val width = size.width
                moveTo(0f, 0f)
                lineTo(width, height)
                lineTo(width, 0f)
            }
            drawIntoCanvas { canvas ->
                canvas.drawOutline(
                    outline = Outline.Generic(trianglePath),
                    paint = Paint().apply {
                        color = Color.Black
                        pathEffect = PathEffect.cornerPathEffect(8.dp.toPx())
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview(){
    StudentHelpcareTheme {
        Surface {
            Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ChatBubble(data = Chat(
                    id = "",
                    text = "Lorem ipsum",
                    created = LocalDate.now().toString(),
                    isMe = true,
                    userProfile = UserProfile(
                        id = "",
                        name = "",
                        imageUrl = "",
                        schoolId = "",
                        username = ""
                    )
                ))
                ChatBubble(data = Chat(
                    id = "",
                    text = "Lorem ipsum",
                    created = LocalDate.now().toString(),
                    isMe = false,
                    userProfile = UserProfile(
                        id = "",
                        name = "",
                        imageUrl = "",
                        schoolId = "",
                        username = ""
                    )
                ))
            }
        }
    }
}