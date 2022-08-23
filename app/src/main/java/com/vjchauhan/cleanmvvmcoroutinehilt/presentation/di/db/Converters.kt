package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.di.db

import androidx.room.TypeConverter
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source):String{
           return source.name
    }
    @TypeConverter
    fun toSource(name:String):Source{
        return Source(name,name)
    }
}
