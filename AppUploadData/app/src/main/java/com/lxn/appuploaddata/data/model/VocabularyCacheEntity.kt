package com.lxn.appuploaddata.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "new_word_toeic_600")
data class VocabularyCacheEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "topic")
    var topic: String,

    @ColumnInfo(name = "id_temp")
    var id_temp: Int,

    @ColumnInfo(name = "vocabulary")
    var vocabulary: String,

    @ColumnInfo(name = "vocalization")
    var vocalization: String,

    @ColumnInfo(name = "explanation")
    var explanation: String,

    @ColumnInfo(name = "transalte")
    var transalte: String,

    @ColumnInfo(name = "example")
    var example: String,

    @ColumnInfo(name = "exampleTranslate")
    var exampleTranslate: String
)