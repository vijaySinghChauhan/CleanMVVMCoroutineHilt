package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.di.db

import androidx.room.*
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ModelItemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: ModelItem)

    @Query("SELECT * FROM list")
    fun getAllList(): Flow<List<ModelItem>>

    @Delete
    suspend fun deleteModel(model: ModelItem)



}