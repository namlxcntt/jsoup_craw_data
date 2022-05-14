package com.lxn.appuploaddata.module

import com.lxn.appuploaddata.feature.repository.MainRepository
import com.lxn.appuploaddata.feature.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by @Author: Nam_Lx
 * Project : AppUploadData
 * Create Time : 11:55 - 14/05/2022
 * For all issue contact me : namlxcntt@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun provideMainRepository(mainRepository: MainRepositoryImpl) : MainRepository
}