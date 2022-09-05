package com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSource

import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ApiResponse
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.spinner.ApiSpinnerResponse
import retrofit2.Response

interface ListRemoteDataSource {
    suspend fun getList():Response<ApiResponse>
    suspend fun getSearchedList(searchQuery:Int):Response<ApiResponse>
    suspend fun getSpinnerList():Response<ApiSpinnerResponse>

}