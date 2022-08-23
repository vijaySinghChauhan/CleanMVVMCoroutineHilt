package com.vjchauhan.cleanmvvmcoroutinehilt.model

data class ModelItem(
    var body: String = "",
    var id: Int = 0,
    var title: String = "",
    var userId: Int = 0,
    var url: String = "https://picsum.photos/200/300.png"
)