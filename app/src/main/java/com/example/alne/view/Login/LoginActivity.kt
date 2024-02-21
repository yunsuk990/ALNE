package com.example.alne.view.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alne.GlobalApplication
import com.example.alne.MainActivity
import com.example.alne.R
import com.example.alne.databinding.ActivityLoginBinding
import com.example.alne.model.Jwt
import com.example.alne.model.Token
import com.example.alne.view.SignUp.SignUpActivity
import com.example.alne.viewmodel.LoginViewModel
import com.example.alne.viewmodel.SignUpViewModel
import com.example.flo.Network.AuthApi
import com.example.flo.Network.AuthResponse
import com.example.flo.Network.getRetrofit
import com.example.flo.model.User
import com.google.gson.Gson
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.math.log


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    //아이디 저장
    private var saveId: Boolean = false
    //자동 로그인
    private var autoLogin: Boolean = false

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        //일반 유저 로그인 성공 여부
        viewModel.loginRespond.observe(this, Observer { res ->
            when(res?.status) {
                200 -> {
                    Toast.makeText(this@LoginActivity, "로그인", Toast.LENGTH_LONG).show()
                    GlobalApplication.prefManager.saveJwt(res.data!!)
                    Log.d("loginRespond", res.data.toString())
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }
                401 -> {
                    Toast.makeText(this@LoginActivity, "아이디 또는 비밀번호가 틀렸습니다", Toast.LENGTH_LONG).show()
                }
            }
            binding.loginPb.visibility = View.INVISIBLE
        })


        //카카오 로그인 성공 여부
        viewModel.kakaoRespond.observe(this, Observer { token ->
            Log.d("kakao_token", token.toString())
            if(token != null){
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        })

        //카카오 유저 정보 서버와 통신 성공 여부
        viewModel.kakaoMyServerRespond.observe(this, Observer { res ->
            when(res.status){
                200 -> {
                    Toast.makeText(this@LoginActivity, "카카오톡 회원가입 성공", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }
                401 -> {
                    Toast.makeText(this@LoginActivity, "카카오톡 회원가입 실패", Toast.LENGTH_LONG).show()
                }
            }
        })


        binding.loginSignUpBt.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.loginAutoLoginIb.setOnClickListener {
            if(autoLogin){
                binding.loginAutoLoginIb.setImageResource(R.drawable.circle)
                autoLogin = !autoLogin
            }else{
                binding.loginAutoLoginIb.setImageResource(R.drawable.checked)
                autoLogin = !autoLogin
            }
        }

        binding.loginSaveIDIb.setOnClickListener {
            if(saveId){
                binding.loginSaveIDIb.setImageResource(R.drawable.circle)
                saveId = !saveId
            }else{
                binding.loginSaveIDIb.setImageResource(R.drawable.checked)
                saveId = !saveId
            }
        }

        binding.loginKakaoBt.setOnClickListener {
            viewModel.kakaoLogin()
        }

        binding.loginGoogleBt.setOnClickListener {

        }

        binding.loginLoginBt.setOnClickListener{
            binding.loginPb.visibility = View.VISIBLE
            viewModel.login(User(binding.loginEmailEt.text.toString(),null, binding.loginPasswordEt.text.toString()))
        }

    }
    override fun onResume() {
        super.onResume()
        var loginsetting = GlobalApplication.prefManager.getLoginSetting()
        saveId = loginsetting[0]
        autoLogin = loginsetting[1]
        if(autoLogin){
            binding.loginAutoLoginIb.setImageResource(R.drawable.checked)
        }else{
            binding.loginAutoLoginIb.setImageResource(R.drawable.circle)
        }
        if(saveId){
            binding.loginSaveIDIb.setImageResource(R.drawable.checked)
        }else{
            binding.loginSaveIDIb.setImageResource(R.drawable.circle)
        }
    }

    override fun onPause() {
        super.onPause()
        GlobalApplication.prefManager.saveLoginSetting(saveId, autoLogin)
    }

}