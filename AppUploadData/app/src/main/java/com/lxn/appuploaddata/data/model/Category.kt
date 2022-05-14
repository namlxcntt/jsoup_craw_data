package com.lxn.appuploaddata.data.model

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Category(
    var categoryId: Int,
    var createDate: String,
    var createBy: String,
    var updateDate: String,
    var updateBy: String,
    var title: String,
    var description: String,
    var image: String,
): Parcelable