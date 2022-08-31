package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.di

import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.ListRepository
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.ListRepositoryImpl
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSource.ListLocalDataSource
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSource.ListRemoteDataSource

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideListRepository(
        listRemoteDataSource: ListRemoteDataSource,
        listLocalDataSource: ListLocalDataSource
    ): ListRepository {
        return ListRepositoryImpl(
            listRemoteDataSource,
            listLocalDataSource
        )
    }

}














