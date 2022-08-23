package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.di

import com.vjchauhan.cleanmvvmcoroutinehilt.data.network.APIService
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSourceImpl.ListRemoteDataSource
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSourceImpl.ListRemoteDataSourceImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun providelistRemoteDataSource(
        listAPIService: APIService
    ): ListRemoteDataSource {
       return ListRemoteDataSourceImpl(listAPIService)
    }

}












