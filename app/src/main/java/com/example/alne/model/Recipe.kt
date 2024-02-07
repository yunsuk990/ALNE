package com.example.alne.model

data class Recipe(
    val recipeCode: Int,
    val name:String,
    val introduce: String,
    val category: String,
    val classification: String,
    val time: String,
    val serving: String,
    val difficulty: String,
    val imageURL: String,
    val calorie: Int,
    val favoritedByUser: Array<Int>
)
