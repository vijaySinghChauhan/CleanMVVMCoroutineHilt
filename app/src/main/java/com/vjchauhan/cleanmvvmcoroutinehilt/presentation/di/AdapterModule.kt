package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.di


import com.vjchauhan.cleanmvvmcoroutinehilt.presentation.adapter.SampleListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
   @Singleton
   @Provides
   fun providelistAdapter(): SampleListAdapter {
       return SampleListAdapter()
   }
}