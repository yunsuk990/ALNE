package com.example.alne.model

import com.google.gson.annotations.SerializedName

data class Jwt(
    @SerializedName(value = "accessToken") val accessToken: String?,
    @SerializedName(value = "refreshToken") val refreshToken: String?,
    @SerializedName(value = "userId") val userId: Int
)
