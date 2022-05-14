package com.lxn.appuploaddata.data.model.dao

import androidx.room.*
import com.lxn.appuploaddata.data.model.CategoryCacheEntity

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: CategoryCacheEntity)

    @Update
    fun update(category: CategoryCacheEntity)

    @Delete
    fun delete(category: CategoryCacheEntity)

    @Query("SELECT * FROM category_newWord")
    suspend fun getAllCategory(): List<CategoryCacheEntity>

    @Query("SELECT * FROM  category_newWord WHERE categoryId = :id ")
    suspend fun getCategoryById(id: Int): CategoryCacheEntity
}