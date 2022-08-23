package com.vjchauhan.cleanmvvmcoroutinehilt.data.network


import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {
  @GET("posts")
  suspend fun getList(
      ): Response<ApiResponse>

    @POST("posts/")
    suspend fun getSearched(
        @Query("a")
        query:String,
    ): Response<ApiResponse>

}