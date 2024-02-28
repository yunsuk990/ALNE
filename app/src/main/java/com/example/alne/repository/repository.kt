package com.example.alne.repository

import android.app.Application
import com.example.alne.Network.AuthApi
import com.example.alne.Network.RecipeApi
import com.example.alne.Network.getRetrofit
import com.example.alne.model.KakaoUser
import com.example.alne.model.User


class repository(val application: Application) {

    private val userService = getRetrofit().create(AuthApi::class.java)
    private val recipeService = getRetrofit().create(RecipeApi::class.java)

    fun signUp(user: User) = userService.signUp(user)

    fun login(user: User) = userService.login(user)

    fun kakaoSignUp(user: KakaoUser) = userService.kakaoSignUp(user)
    fun kakaoLogin(user: KakaoUser) = userService.kakaoLogin(user)

    fun getAllRecipe() = recipeService.getAllRecipe()
}
