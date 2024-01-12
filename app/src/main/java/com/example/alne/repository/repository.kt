package com.example.alne.repository

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.alne.MainActivity
import com.example.alne.Network.FridgeApi
import com.example.alne.model.Food
import com.example.alne.model.KakaoUser
import com.example.alne.model.Token
import com.example.alne.model.UserId
import com.example.flo.Network.AuthApi
import com.example.flo.Network.AuthResponse
import com.example.flo.Network.getRetrofit
import com.example.flo.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class repository {

    private val userService = getRetrofit().create(AuthApi::class.java)
    private val fridgeService = getRetrofit().create(FridgeApi::class.java)

    fun signUp(user: User) = userService.signUp(user)

    fun login(user: User) = userService.login(user)

    fun addFridgeData(accessToken: String, food: Food) = fridgeService.addFridgeFood(accessToken,food)

    fun getFridgeFood(accessToken: String, userId: UserId) = fridgeService.getFridgeFood(accessToken,userId)


    fun kakaoSignUp(user: KakaoUser) = userService.kakaoSignUp(user)
    fun kakaoLogin(user: KakaoUser) = userService.kakaoLogin(user)


}
