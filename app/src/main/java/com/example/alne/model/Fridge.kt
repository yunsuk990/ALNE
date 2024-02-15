package com.example.alne.model

import com.example.flo.model.User

data class Fridge(
    val ingredient: Ingredient,
    val exp: String,
    val addDate: String,
    val storage: String,
    val memo: String,
)

data class UserId(
    val userId: Int,
    val data: Int?
)