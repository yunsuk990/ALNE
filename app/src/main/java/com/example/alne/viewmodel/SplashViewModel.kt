package com.example.alne.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alne.Network.AuthResponse
import com.example.alne.model.KakaoUser
import com.example.alne.repository.repository
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashViewModel(private var application: Application) : AndroidViewModel(application) {

    private val repository = repository(application)
    private val _kakaoMyServerLoginRespond = MutableLiveData<AuthResponse>()
    val kakaoMyServerLoginRespond: LiveData<AuthResponse> = _kakaoMyServerLoginRespond

    fun kakaoLogin(){
        UserApiClient.instance.me { user, error ->
            Log.d("kakao_id", user?.id.toString())
            Log.d("kakao_nickname", user?.kakaoAccount?.name.toString() )
            Log.d("kakao_age", user?.kakaoAccount?.profile?.nickname.toString() )
            Log.d("kakao_email", user?.kakaoAccount?.profile?.profileImageUrl.toString())
            repository.kakaoLogin(KakaoUser(user?.id!!, user?.kakaoAccount?.name.toString(), user?.kakaoAccount?.email.toString())).enqueue(object: Callback<AuthResponse>{
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>,
                ) {
                    _kakaoMyServerLoginRespond.value = response.body()
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Log.d("kakaoLogin", t.message.toString())
                }

            })
        }
    }

}