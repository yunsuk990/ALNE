package com.example.alne.model

import com.example.flo.model.User

data class Fridge(
    val user: FridgeUser,
    val ingredient: Ingredient,
    val exp: String,
    val storage: String,
    val memo: String,
)

//삭제

data class FridgeUser(
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val account : String
)

data class UserId(
    val userId: Int
)
