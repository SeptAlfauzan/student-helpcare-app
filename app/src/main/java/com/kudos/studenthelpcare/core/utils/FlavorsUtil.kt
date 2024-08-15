package com.kudos.studenthelpcare.core.utils

import android.content.Context
import android.util.Log
import com.kudos.studenthelpcare.BuildConfig
import java.util.Properties

class FlavorUtils {
    companion object {
        fun printFlavorName() {
            Log.d("FlavorUtils", BuildConfig.FLAVOR)
        }


        fun Context.loadFlavorProperties( key: String): String {
            val properties = Properties()
            val flavor = BuildConfig.FLAVOR
            try {
                val inputStream = this.assets.open("$flavor.properties")
                properties.load(inputStream)
                inputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return properties.getProperty(key) ?: ""
        }

    }
}