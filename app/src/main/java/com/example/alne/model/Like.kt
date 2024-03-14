package com.example.alne.model

data class LikeRespond(
    val status: Int,
    val data: Like
)

data class Like(
    val id: Int,
    val user: UserInfo,
    val recipe: Recipe,
    val like: Boolean
)


