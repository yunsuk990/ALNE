package com.example.alne.model

data class Jwt(
    val accessToken: String,
    val refreshToken: String,
    val userId: Int
)
