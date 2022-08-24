package com.vjchauhan.cleanmvvmcoroutinehilt.data.network


import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ApiResponse
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIService {
  @GET("posts")
  suspend fun getList(
      ): Response<ApiResponse>


    @GET("posts")
   suspend fun getSearched(
        @Query("userId") query:Int
    ): Response<ApiResponse>
}