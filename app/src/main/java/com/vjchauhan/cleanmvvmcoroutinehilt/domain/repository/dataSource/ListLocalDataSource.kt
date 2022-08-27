package com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSourceImpl


import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ApiResponse
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ListLocalDataSource {
    suspend fun saveItemToDB(model: ModelItem)
    fun getSavedItems(): Flow<List<ModelItem>>
    suspend fun deleteItemsFromDB(article: ModelItem)


}