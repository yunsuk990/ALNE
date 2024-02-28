package com.example.alne.view.MyPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alne.databinding.FragmentMyPageBinding
import com.example.alne.view.Login.LoginActivity
import com.example.alne.view.SignUp.SignUpActivity
import com.example.alne.view.Splash.StartActivity
import com.example.alne.viewmodel.MyPageViewModel
import com.kakao.sdk.user.UserApiClient

class MyPageFragment : Fragment() {
    lateinit var binding: FragmentMyPageBinding
    lateinit var viewModel: MyPageViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyPageBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MyPageViewModel::class.java)

        viewModel.authStateRespond.observe(viewLifecycleOwner, Observer { state ->
            if(state){
                binding.login.text = "로그아웃"
                binding.login.setOnClickListener {
                    LogoutCustomDialog().show(requireActivity().supportFragmentManager, LogoutCustomDialog.TAG)
                }
            }else{
                binding.login.text = "로그인"
            }
        })

        binding.myPageProfileSettingCv.setOnClickListener {
            startActivity(Intent(context, UserProfileActivity::class.java))
        }


//        binding.login.setOnClickListener {
//            startActivity(Intent(requireContext(), LoginActivity::class.java))
//        }
//
        return binding.root
    }
}