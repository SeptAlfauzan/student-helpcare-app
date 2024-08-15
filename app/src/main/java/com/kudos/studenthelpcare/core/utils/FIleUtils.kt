package com.kudos.studenthelpcare.core.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


object FileUtils {
    fun getFileFromRaw(context: Context, resId: Int, fileName: String?): File? {
        val resources = context.resources
        var file: File? = null
        try {
            // Open the audio file from the raw folder
            val inputStream = resources.openRawResource(resId)
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes)
            inputStream.close()

            // Create a new File Object
            file = File(context.getExternalFilesDir(null), fileName)
            val outputStream = FileOutputStream(file)
            outputStream.write(bytes)
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }
}
