package com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository

import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ApiResponse
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import com.vjchauhan.cleanmvvmcoroutinehilt.utils.Resource

import kotlinx.coroutines.flow.Flow

interface ListRepository{

      suspend fun getList(): Resource<ApiResponse>
      suspend fun getSearchedList(searchQuery:String) : Resource<ApiResponse>

      suspend fun saveList(model: ModelItem)
      suspend fun deleteList(model: ModelItem)
      fun getSavedList(): Flow<List<ModelItem>>
}