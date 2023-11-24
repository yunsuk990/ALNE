package com.example.alne.model

data class Food(
    var name: String,
    var image: Int?,
    var memo: String? = null,
    var place: String? = null,
    var expireDate: String,
)
