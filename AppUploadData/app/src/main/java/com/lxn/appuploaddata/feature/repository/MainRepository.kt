package com.lxn.appuploaddata.feature.repository

import com.lxn.appuploaddata.data.model.Category
import com.lxn.appuploaddata.data.model.CategoryCacheEntity
import com.lxn.appuploaddata.data.model.Vocabulary
import com.lxn.appuploaddata.data.model.VocabularyCacheEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by @Author: Nam_Lx
 * Project : AppUploadData
 * Create Time : 11:54 - 14/05/2022
 * For all issue contact me : namlxcntt@gmail.com
 */
interface MainRepository {
    suspend fun getAllCategory(): Flow<List<Category>>

    suspend fun getAllVocabularyById(): Flow<List<Vocabulary>>

    suspend fun saveImageCategory(category: Category): Flow<Category>

    suspend fun saveCategoryToFirebase(category: Category): Flow<Boolean>

    suspend fun saveImageVocabulary(vocabulary: Vocabulary): Flow<Vocabulary>

    suspend fun saveAudioVocabulary(vocabulary: Vocabulary): Flow<Vocabulary>

    suspend fun saveVocabularyToFirebase(vocabulary: Vocabulary): Flow<Boolean>

}