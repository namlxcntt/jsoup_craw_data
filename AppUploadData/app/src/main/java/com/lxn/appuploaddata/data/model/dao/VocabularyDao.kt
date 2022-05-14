package com.lxn.appuploaddata.data.model.dao

import androidx.room.*
import com.lxn.appuploaddata.data.model.VocabularyCacheEntity

@Dao
interface VocabularyDao {

    @Insert
    fun insert(vocab: VocabularyCacheEntity)

    @Update
    fun update(vocab: VocabularyCacheEntity)

    @Delete
    fun delete(vocab: VocabularyCacheEntity)

    @Query("SELECT * FROM  new_word_toeic_600")
    suspend fun getAllNewWord(): List<VocabularyCacheEntity>

    @Query("SELECT * FROM  new_word_toeic_600 WHERE topic =:topic ")
    suspend fun getAllNewWordByCategory(topic: String): List<VocabularyCacheEntity>

    @Query("SELECT * FROM  new_word_toeic_600 WHERE id_temp = :id ")
    suspend fun getAllNewWordByIDy(id: Int): VocabularyCacheEntity

}