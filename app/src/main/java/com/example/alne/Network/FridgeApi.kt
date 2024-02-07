package com.example.alne.Network

import com.example.alne.model.Food
import com.example.alne.model.UserId
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface FridgeApi {

    @POST("/fridge")
    fun addFridgeFood(
        @Header("accessToken") accessToken: String,
        @Body food: Food
    ): Call<FridgePostResponse>

    @POST("/getFridge")
    fun getFridgeFood(
        @Header("accessToken") accessToken: String,
        @Body userId: UserId
    ): Call<FridgeGetResponse>

    @POST("/delFridge")
    fun deleteFridgeFood(
        @Body userId: UserId
    ): Call<FridgePostResponse>

}