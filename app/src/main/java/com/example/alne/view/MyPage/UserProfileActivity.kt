package com.example.alne.view.MyPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alne.R
import com.example.alne.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userProfileTb.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}