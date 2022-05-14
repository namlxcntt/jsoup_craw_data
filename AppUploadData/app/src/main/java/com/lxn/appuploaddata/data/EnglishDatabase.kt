package com.lxn.appuploaddata.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lxn.appuploaddata.data.model.CategoryCacheEntity
import com.lxn.appuploaddata.data.model.VocabularyCacheEntity
import com.lxn.appuploaddata.data.model.dao.CategoryDao
import com.lxn.appuploaddata.data.model.dao.VocabularyDao

@Database(
    entities = [
        CategoryCacheEntity::class,
        VocabularyCacheEntity::class
    ], version = 3,exportSchema = false
)
abstract class EnglishDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun vocabularyDao(): VocabularyDao
}