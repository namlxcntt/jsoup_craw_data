package com.lxn.appuploaddata.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_newWord")
data class CategoryCacheEntity(

    @PrimaryKey()
    @ColumnInfo(name = "categoryId")
    var categoryId: Int,

    @ColumnInfo(name = "createDate")
    var createDate: String,

    @ColumnInfo(name = "createBy")
    var createBy: String,

    @ColumnInfo(name = "updateDate")
    var updateDate: String,

    @ColumnInfo(name = "updateBy")
    var updateBy: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "image")
    var image: String
)