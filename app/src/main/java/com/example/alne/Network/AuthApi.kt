package com.example.alne.Network

import com.example.alne.model.KakaoUser
import com.example.alne.model.User
import com.example.alne.Network.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/signup")
    fun signUp(@Body user: User): Call<AuthResponse>

    @POST("/login")
    fun login(@Body user: User): Call<LoginResponse>

    @POST("/kakaoSignUp")
    fun kakaoSignUp(@Body user: KakaoUser): Call<AuthResponse>

    @POST("/kakaoLogin")
    fun kakaoLogin(@Body user: KakaoUser): Call<AuthResponse>

}