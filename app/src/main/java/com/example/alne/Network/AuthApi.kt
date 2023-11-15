package com.example.flo.Network

import com.example.alne.model.Token
import com.example.flo.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/signup")
    fun signUp(@Body user: User): Call<AuthResponse>

    @POST("/login")
    fun login(@Body user: User): Call<AuthResponse>

    @POST("/token")
    fun accessToken(@Body token: Token): Call<AuthResponse>



}