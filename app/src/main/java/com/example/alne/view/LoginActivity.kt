package com.example.alne.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.alne.MainActivity
import com.example.alne.databinding.ActivityLoginBinding
import com.example.flo.Network.AuthInterface
import com.example.flo.Network.AuthResponse
import com.example.flo.Network.getRetrofit
import com.example.flo.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        retrofit = getRetrofit()
        setContentView(binding.root)

        binding.loginSignUpBt.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }


        binding.loginLoginBt.setOnClickListener{
            retrofit.create(AuthInterface::class.java).login(User(binding.loginEmailEt.text.toString(),  binding.loginPasswordEt.text.toString())).enqueue(object: Callback<AuthResponse>{
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>,
                ) {
                    if(response.isSuccessful){
                        var res = response.body()
                        when(res?.status) {
                            200 -> {
                                Toast.makeText(this@LoginActivity, "로그인.", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            }
                            401 -> {

                            }
                        }
                        Log.d("login_success", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Log.d("login_failure", t.toString())
                }

            })

        }


    }
}