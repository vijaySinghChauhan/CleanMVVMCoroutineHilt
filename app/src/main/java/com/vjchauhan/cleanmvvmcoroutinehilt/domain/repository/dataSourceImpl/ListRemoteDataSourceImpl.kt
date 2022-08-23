package com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSourceImpl


import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ApiResponse
import com.vjchauhan.cleanmvvmcoroutinehilt.data.network.APIService
import retrofit2.Response

class ListRemoteDataSourceImpl(
        private val listAPIService: APIService
): ListRemoteDataSource {
    override suspend fun getList(): Response<ApiResponse> {
          return listAPIService.getList()
    }



    override suspend fun getSearchedList(

        searchQuery: String,

    ): Response<ApiResponse> {
        return listAPIService.getSearched(searchQuery)
    }
}