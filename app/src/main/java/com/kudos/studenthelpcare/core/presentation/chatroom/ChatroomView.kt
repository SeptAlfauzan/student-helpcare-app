package com.kudos.studenthelpcare.core.presentation.chatroom

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.kudos.studenthelpcare.ui.theme.StudentHelpcareTheme

@Composable
fun ChatroomView(){

}


@Preview(device= Devices.PIXEL_4)
@Composable
private fun Preview(){
    StudentHelpcareTheme {
        Surface {
            ChatroomView()
        }
    }
}