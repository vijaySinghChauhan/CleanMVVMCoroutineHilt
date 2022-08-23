package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.di

import com.vjchauhan.cleanmvvmcoroutinehilt.BuildConfig
import com.vjchauhan.cleanmvvmcoroutinehilt.data.network.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
         return Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl(BuildConfig.BASE_URL)
             .build()
    }

    @Singleton
    @Provides
    fun providelistAPIService(retrofit: Retrofit):APIService{
        return retrofit.create(APIService::class.java)
    }

}













