package com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSourceImpl

import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ApiResponse
import retrofit2.Response

interface ListRemoteDataSource {
    suspend fun getList():Response<ApiResponse>
    suspend fun getSearchedList(searchQuery:String):Response<ApiResponse>
}