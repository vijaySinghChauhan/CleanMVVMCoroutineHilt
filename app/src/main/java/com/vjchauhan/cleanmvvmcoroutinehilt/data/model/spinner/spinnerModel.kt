package com.vjchauhan.cleanmvvmcoroutinehilt.data.model.spinner

data class spinnerModel(
    var `data`: List<Data> = listOf(),
    var error: Boolean = false,
    var msg: String = ""
)