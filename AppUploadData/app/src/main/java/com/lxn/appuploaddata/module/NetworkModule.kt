package com.lxn.appuploaddata.module

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by @Author: Nam_Lx
 * Project : AppUploadData
 * Create Time : 13:02 - 14/05/2022
 * For all issue contact me : namlxcntt@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun providerFirebaseDataBase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    fun providerDatabaseReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.reference
    }

    @Provides
    fun providerStorageReference(): FirebaseStorage = Firebase.storage
}