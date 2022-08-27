package com.vjchauhan.cleanmvvmcoroutinehilt.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem

@Database(
    entities = [ModelItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract  class ArticleDatabase : RoomDatabase(){
    abstract fun getArticleDAO(): ModelItemDAO
}

