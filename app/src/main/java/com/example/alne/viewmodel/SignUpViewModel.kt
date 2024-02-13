package com.example.alne.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alne.repository.repository
import com.example.flo.Network.AuthResponse
import com.example.flo.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel(private var application: Application) : AndroidViewModel(application) {

    private val repository = repository(application)
    private val _signUpRespond = MutableLiveData<AuthResponse>()
    val signUpRespond: LiveData<AuthResponse> = _signUpRespond

    fun signUp(user: User){
        repository.signUp(user).enqueue(object: Callback<AuthResponse>{
            override fun onResponse(
                call: Call<AuthResponse>,
                response: Response<AuthResponse>,
            ) {
                var res = response.body()
                _signUpRespond.value = res
                Log.d("signUp_success", res.toString())
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("signUp_failure", t.message.toString())
            }
        })
    }
}