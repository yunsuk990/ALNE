package com.example.alne.Network

import com.example.alne.model.KakaoUser
import com.example.alne.model.User
import com.example.alne.Network.AuthResponse
import com.example.alne.model.Id
import com.example.alne.model.ProfileRespond
import com.example.alne.model.UserId
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AuthApi {

    @POST("/signup")
    fun signUp(@Body user: User): Call<AuthResponse>

    @POST("/login")
    fun login(@Body user: User): Call<LoginResponse>

    @POST("/kakaoSignUp")
    fun kakaoSignUp(@Body user: KakaoUser): Call<AuthResponse>

    @POST("/kakaoLogin")
    fun kakaoLogin(@Body user: KakaoUser): Call<AuthResponse>

    @Multipart
    @POST("/userImage")
    fun saveUserProfileImage(
        @Part file: MultipartBody.Part,
        @Part("userId") userId: RequestBody
    ): Call<AuthResponse>


    @POST("/profile")
    fun getUserProfile(
        @Body userId: UserId
    ):Call<ProfileRespond>


}