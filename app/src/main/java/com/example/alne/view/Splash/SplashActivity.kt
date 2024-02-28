package com.example.alne.view.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alne.MainActivity
import com.example.alne.databinding.ActivitySplashBinding
import com.example.alne.viewmodel.SplashViewModel
import com.example.alne.Network.getRetrofit
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import retrofit2.Retrofit

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    lateinit var retrofit: Retrofit
    lateinit var viewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        retrofit = getRetrofit()
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        viewModel.kakaoMyServerLoginRespond.observe(this, Observer {  res ->
            when(res.status){
                200 -> {
                    Toast.makeText(this@SplashActivity, "로그인", Toast.LENGTH_LONG).show()
//                    saveJwt(res.data!!)
                    Log.d("loginRespond", res.data.toString())
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                }
                401 -> {
                    Toast.makeText(this@SplashActivity, "아이디 또는 비밀번호가 틀렸습니다", Toast.LENGTH_LONG).show()
                }
            }
        })



        var handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            checkKakaoLogin()
        }
        ,1000)
    }

    fun checkKakaoLogin(){
        if(AuthApiClient.instance.hasToken()){
            UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                Log.d("token", tokenInfo.toString()) //42582
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError() == true) {
                        //로그인 필요
                        startActivity(Intent(this, StartActivity::class.java))
                    }
                    else {
                        //기타 에러
                    }
                }else {
                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                    viewModel.kakaoLogin()
                }
            }
        }else {

            //로그인 필요
            if(autoLogin()){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                startActivity(Intent(this, StartActivity::class.java))
            }
        }
    }

    fun autoLogin(): Boolean{
        val sharedPreferences = getSharedPreferences("login_setting", MODE_PRIVATE)
        return sharedPreferences.getBoolean("autoLogin", false)
    }

}