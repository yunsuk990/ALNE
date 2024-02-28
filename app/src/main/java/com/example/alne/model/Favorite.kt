package com.example.alne.model

data class FavoriteRespond(
    val status: Int,
    val data: Favorite
)

data class Favorite(
    val id: Int,
    val user: UserInfo,
    val recipe: Recipe
)

data class UserInfo(
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val account: String,
    val favoriteRecipes: List<Int> = ArrayList(),
    val likeRecipes: List<Int> = ArrayList()
)
