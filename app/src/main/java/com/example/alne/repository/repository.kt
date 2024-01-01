package com.example.alne.repository

import com.example.flo.Network.AuthApi
import com.example.flo.Network.getRetrofit
import com.example.flo.model.User

class repository {

    private val userService = getRetrofit().create(AuthApi::class.java)

    fun signUp(user: User) = userService.signUp(user)

    fun login(user: User) = userService.login(user)
}