package com.example.alne.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class recipe (
    @PrimaryKey val recipe_code: Int,
    val time: Int,
    val difficulty: String,
    val serving: String,
    val name:String,
    val imageurl: String,
    val introduce: String,
    val category: String,
    val classification: String,
    val calorie: Int,
)