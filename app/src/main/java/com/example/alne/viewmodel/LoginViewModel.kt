package com.example.alne.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alne.Network.AuthResponse
import com.example.alne.Network.LoginResponse
import com.example.alne.model.KakaoUser
import com.example.alne.model.User
import com.example.alne.repository.repository
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = repository(application)
    private val _loginRespond = MutableLiveData<LoginResponse>()
    val loginRespond: LiveData<LoginResponse> = _loginRespond

    private val _kakaoTokenRespond = MutableLiveData<OAuthToken>()
    val kakaoRespond: LiveData<OAuthToken> = _kakaoTokenRespond

    private val _kakaoMyServerRespond = MutableLiveData<AuthResponse>()
    val kakaoMyServerRespond: LiveData<AuthResponse> = _kakaoMyServerRespond

    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.d("kakako", "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.d("kakako", "카카오계정으로 로그인 성공 ${token.accessToken}")
//            sendAccessToken(Token(token.toString()))
            //토큰 저장하기
            getKakaoUserInfo()
            _kakaoTokenRespond.postValue(token)
        }
    }

    fun login(user: User){
        repository.login(user).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>,
            ) {
                var res = response.body()
                _loginRespond.value = res
                Log.d("login_success", res.toString())
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("login_failure", t.message.toString())

            }
        })
    }
    fun kakaoLogin() {
        Log.d("kakako", UserApiClient.instance.isKakaoTalkLoginAvailable(getApplication<Application>().applicationContext).toString())
        if(UserApiClient.instance.isKakaoTalkLoginAvailable(getApplication<Application>().applicationContext)){
            UserApiClient.instance.loginWithKakaoTalk(getApplication<Application>().applicationContext) { token, error ->
                if (error != null) {
                    Log.e("kakako", "로그인 실패", error)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(getApplication<Application>().applicationContext, callback = callback)
                }
                else if (token != null) {
                    Log.i("kakako", "로그인 성공 ${token.accessToken}")
                }
            }
        }else{
            UserApiClient.instance.loginWithKakaoAccount(getApplication<Application>().applicationContext, callback = callback)
        }
    }

    fun kakaoSignUp(user: KakaoUser){
        repository.kakaoSignUp(user).enqueue(object: Callback<AuthResponse>{
            override fun onResponse(
                call: Call<AuthResponse>,
                response: Response<AuthResponse>,
            ) {
                _kakaoMyServerRespond.value = response.body()
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("kakaoSignup", t.message.toString())
            }

        })
    }
    fun getKakaoUserInfo(){
        UserApiClient.instance.me { user, error ->
            Log.d("kakao_id", user?.id.toString())
            Log.d("kakao_nickname", user?.kakaoAccount?.name.toString() )
            Log.d("kakao_age", user?.kakaoAccount?.profile?.nickname.toString() )
            Log.d("kakao_email", user?.kakaoAccount?.profile?.profileImageUrl.toString() )
            kakaoSignUp(KakaoUser(user?.id!!, user?.kakaoAccount?.name.toString(), user?.kakaoAccount?.email.toString()))
        }
    }

}