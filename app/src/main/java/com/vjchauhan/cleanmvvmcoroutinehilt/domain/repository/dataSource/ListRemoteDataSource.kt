package com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSourceImpl

import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ApiResponse
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import retrofit2.Response

interface ListRemoteDataSource {
    suspend fun getList():Response<ApiResponse>
    suspend fun getSearchedList(searchQuery:Int):Response<ApiResponse>
}