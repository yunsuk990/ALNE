package com.example.alne.repository

import com.example.alne.Network.FridgeApi
import com.example.alne.model.Food
import com.example.alne.model.KakaoUser
import com.example.alne.model.UserId
import com.example.flo.Network.AuthApi
import com.example.flo.Network.RecipeApi
import com.example.flo.Network.getRetrofit
import com.example.flo.model.User

class repository {

    private val userService = getRetrofit().create(AuthApi::class.java)
    private val fridgeService = getRetrofit().create(FridgeApi::class.java)
    private val recipeService = getRetrofit().create(RecipeApi::class.java)

    fun signUp(user: User) = userService.signUp(user)

    fun login(user: User) = userService.login(user)

    fun addFridgeData(accessToken: String, food: Food) = fridgeService.addFridgeFood(accessToken,food)

    fun getFridgeFood(accessToken: String, userId: UserId) = fridgeService.getFridgeFood(accessToken,userId)

    fun deleteFridgeFood(userId: UserId) = fridgeService.deleteFridgeFood(userId)


    fun kakaoSignUp(user: KakaoUser) = userService.kakaoSignUp(user)
    fun kakaoLogin(user: KakaoUser) = userService.kakaoLogin(user)

    fun getAllRecipe() = recipeService.getAllRecipe()


}
