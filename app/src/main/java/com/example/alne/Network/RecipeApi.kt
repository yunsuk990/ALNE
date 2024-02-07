package com.example.flo.Network

import com.example.flo.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET


interface RecipeApi {

    @GET("/recipe")
    fun getAllRecipe(): Call<RecipeResponse>

}