package com.example.alne.repository

import com.example.alne.Network.FridgeApi
import com.example.alne.model.Food
import com.example.alne.model.UserId
import com.example.flo.Network.getRetrofit

class fridgeRepository() {

    private val fridgeService = getRetrofit().create(FridgeApi::class.java)

    fun addFridgeData(accessToken: String, food: Food) = fridgeService.addFridgeFood(accessToken,food)

    fun getFridgeFood(accessToken: String, userId: UserId) = fridgeService.getFridgeFood(accessToken,userId)

    fun deleteFridgeFood(userId: UserId) = fridgeService.deleteFridgeFood(userId)

}