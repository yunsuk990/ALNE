package com.example.alne.Network

import com.example.alne.model.Jwt
import com.google.gson.annotations.SerializedName


data class AuthResponse(
    @SerializedName(value = "status") val status: Int,
    @SerializedName(value = "data") val data: Int
)

data class LoginResponse(
    @SerializedName(value = "status") val status: Int,
    @SerializedName(value = "data") val data: Jwt?
)
