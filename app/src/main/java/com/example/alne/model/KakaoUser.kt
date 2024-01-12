package com.example.alne.model

import com.google.gson.annotations.SerializedName

data class KakaoUser(
    @SerializedName(value = "id") val id: Long,
    @SerializedName(value = "name") val name: String,
    @SerializedName(value = "email") val email: String
)
