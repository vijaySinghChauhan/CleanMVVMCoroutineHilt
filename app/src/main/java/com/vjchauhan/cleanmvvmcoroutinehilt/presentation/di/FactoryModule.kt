package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.di

import android.app.Application
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.usecases.*
import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.vm.ListViewModelFactory

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
  fun providelistViewModelFactory(
        application: Application,
        getlistHeadlinesUseCase: GetListUseCase,
        getSearchedlistUseCase: GetSearchedListUseCase,
        savelistUseCase: SaveListUseCase,
        getSavedListUseCase: GetSavedListUseCase,
        deleteSavedlistUseCase: DeleteSavedListUseCase
  ): ListViewModelFactory {
      return ListViewModelFactory(
          application,
          getlistHeadlinesUseCase,
          getSearchedlistUseCase,
          savelistUseCase,
          getSavedListUseCase,
          deleteSavedlistUseCase
      )
  }

}








