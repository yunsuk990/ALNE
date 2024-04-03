package com.example.alne.model


data class ProfileRespond(
    val status: Int,
    val data: Profile
)
data class Profile(
    val id: Int,
    val email: String,
    val name: String,
    val account: String,
    val image: String
)
