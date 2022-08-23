package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.di

import com.vjchauhan.cleanmvvmcoroutinehilt.domain.repository.ListRepository
import com.vjchauhan.cleanmvvmcoroutinehilt.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
   @Singleton
   @Provides
   fun provideGetListUseCase(
       listRepository: ListRepository
   ): GetListUseCase {
      return GetListUseCase(listRepository)
   }

   @Singleton
   @Provides
   fun provideGetSearchedListUseCase(
      listRepository: ListRepository
   ): GetSearchedListUseCase {
      return GetSearchedListUseCase(listRepository)
   }

   @Singleton
   @Provides
   fun provideSaveListUseCase(
      listRepository: ListRepository
   ): SaveListUseCase {
      return SaveListUseCase(listRepository)
   }

   @Singleton
   @Provides
   fun provideGetSavedListUseCase(
      listRepository: ListRepository
   ): GetSavedListUseCase {
      return GetSavedListUseCase(listRepository)
   }

   @Singleton
   @Provides
   fun provideDeleteSavedListUseCase(
      listRepository: ListRepository
   ): DeleteSavedListUseCase {
      return DeleteSavedListUseCase(listRepository)
   }
}
