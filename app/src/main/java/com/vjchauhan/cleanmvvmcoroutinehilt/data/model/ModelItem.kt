package com.vjchauhan.cleanmvvmcoroutinehilt.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "list"
)
data class ModelItem(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("userId")
    var userId: Int = 0,
    @SerializedName("body")
    var body: String = "",
    @SerializedName("url")
    var url: String = "https://picsum.photos/200/300.png"
): Serializable