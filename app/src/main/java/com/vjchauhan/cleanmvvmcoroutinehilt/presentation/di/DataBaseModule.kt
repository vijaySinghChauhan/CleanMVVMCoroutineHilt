package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.di

import android.app.Application
import androidx.room.Room
import com.vjchauhan.cleanmvvmcoroutinehilt.data.db.ArticleDatabase
import com.vjchauhan.cleanmvvmcoroutinehilt.data.db.ModelItemDAO

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun providelistDatabase(app: Application): ArticleDatabase {
        return Room.databaseBuilder(app, ArticleDatabase::class.java, "list_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providelistDao(articleDatabase: ArticleDatabase): ModelItemDAO {
        return articleDatabase.getArticleDAO()
    }


}