package com.lxn.appuploaddata.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vocabulary(
    var id: Int,
    var topic: String,
    var id_temp: Int,
    var vocabulary: String,
    var vocalization: String,
    var explanation: String,
    var transalte: String,
    var example: String,
    var exampleTranslate: String,
    var image: String? = null,
    var linkMp3: String? = null
) : Parcelable