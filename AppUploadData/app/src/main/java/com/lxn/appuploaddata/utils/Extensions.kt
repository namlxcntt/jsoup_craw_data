package com.lxn.appuploaddata.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.util.*

/**
 * Created by @Author: Nam_Lx
 * Project : AppUploadData
 * Create Time : 14:56 - 14/05/2022
 * For all issue contact me : namlxcntt@gmail.com
 */
fun getFileAudioByName(context: Context, name: String): InputStream? {
    return try {
        context.assets.open("audio/${name.lowercase(Locale.getDefault()).trim()}.mp3")
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}