package com.example.flo.Network

import com.example.alne.model.Jwt
import com.example.alne.model.Recipe
import com.google.gson.annotations.SerializedName


data class RecipeResponse(
    @SerializedName(value = "status") val status: Int,
    @SerializedName(value = "data") val data: ArrayList<Recipe>
)