package com.example.alne.repository

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alne.Network.FridgeApi
import com.example.alne.Network.FridgeGetResponse
import com.example.alne.model.Food
import com.example.alne.model.Jwt
import com.example.alne.model.KakaoUser
import com.example.alne.model.UserId
import com.example.flo.Network.AuthApi
import com.example.flo.Network.RecipeApi
import com.example.flo.Network.getRetrofit
import com.example.flo.model.User
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class repository(val application: Application) {

    private val userService = getRetrofit().create(AuthApi::class.java)
    private val recipeService = getRetrofit().create(RecipeApi::class.java)

    fun signUp(user: User) = userService.signUp(user)

    fun login(user: User) = userService.login(user)

    fun kakaoSignUp(user: KakaoUser) = userService.kakaoSignUp(user)
    fun kakaoLogin(user: KakaoUser) = userService.kakaoLogin(user)

    fun getAllRecipe() = recipeService.getAllRecipe()
}
