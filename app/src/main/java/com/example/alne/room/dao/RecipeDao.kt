package com.example.alne.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.alne.room.model.food
import com.example.alne.room.model.recipe


@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    fun getAll(): List<recipe>
}