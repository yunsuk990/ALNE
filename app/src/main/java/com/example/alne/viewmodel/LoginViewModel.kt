package com.example.alne.viewmodel

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alne.MainActivity
import com.example.alne.repository.repository
import com.example.flo.Network.AuthResponse
import com.example.flo.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {

    private val repository = repository()
    private val _loginRespond = MutableLiveData<AuthResponse>()
    val loginRespond: LiveData<AuthResponse> = _loginRespond

    fun login(user: User){
        repository.signUp(user).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(
                call: Call<AuthResponse>,
                response: Response<AuthResponse>,
            ) {
                var res = response.body()
                _loginRespond.value = res
                Log.d("login_success", res.toString())
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("login_failure", t.message.toString())

            }
        })
    }
}