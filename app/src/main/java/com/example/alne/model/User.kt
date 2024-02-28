package com.example.alne.model


data class User(
    val email : String,
    val name: String?,
    val password: String,
)

data class Status(
    val status: Int
)