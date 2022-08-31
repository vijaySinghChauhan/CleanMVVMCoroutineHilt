package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.di

import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSource.ListLocalDataSource
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.dataSourceImpl.ListLocalDataSourceImpl
import com.vjchauhan.cleanmvvmcoroutinehilt.data.db.ModelItemDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(modelItemDAO: ModelItemDAO): ListLocalDataSource {
      return ListLocalDataSourceImpl(modelItemDAO)
    }

}













