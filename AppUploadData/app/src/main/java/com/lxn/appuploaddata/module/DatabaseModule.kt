package com.lxn.appuploaddata.module

import android.app.Application
import com.lxn.appuploaddata.data.EnglishDatabase
import com.lxn.appuploaddata.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.ueen.roomassethelper.RoomAssetHelper

/**
 * Created by @Author: Nam_Lx
 * Project : AppUploadData
 * Create Time : 11:41 - 14/05/2022
 * For all issue contact me : namlxcntt@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun providerVocabDatabase(application: Application): EnglishDatabase {
        return RoomAssetHelper.databaseBuilder(
            application.applicationContext,
            EnglishDatabase::class.java,
            Constant.DB_NAME_WORD,
            version = Constant.VERSION_DATABASE
        ).build()
    }
}