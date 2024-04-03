package com.example.alne.repository

import android.app.Application
import com.example.alne.Network.AuthApi
import com.example.alne.Network.RecipeApi
import com.example.alne.Network.getRetrofit
import com.example.alne.model.KakaoUser
import com.example.alne.model.User
import com.example.alne.model.UserId
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Part


class repository(val application: Application) {

    private val userService = getRetrofit().create(AuthApi::class.java)


    fun signUp(user: User) = userService.signUp(user)

    fun login(user: User) = userService.login(user)

    fun kakaoSignUp(user: KakaoUser) = userService.kakaoSignUp(user)
    fun kakaoLogin(user: KakaoUser) = userService.kakaoLogin(user)

    fun saveUserProfileImage(file: MultipartBody.Part, userId: RequestBody) = userService.saveUserProfileImage(file,userId)

    fun getUserProfile(userId: UserId) = userService.getUserProfile(userId)

}
