package com.example.alne.repository

import com.example.alne.GlobalApplication
import com.example.alne.Network.FridgeApi
import com.example.alne.room.model.recipe
import com.example.flo.Network.RecipeApi
import com.example.flo.Network.getRetrofit

class recipeRepository {

    private val recipeService = getRetrofit().create(RecipeApi::class.java)

    fun getAllRecipe(): ArrayList<recipe> = GlobalApplication.appDatabase.recipeDao().getAll() as ArrayList<recipe>
}