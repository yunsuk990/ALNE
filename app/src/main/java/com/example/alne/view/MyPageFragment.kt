package com.example.alne.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alne.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    lateinit var binding: FragmentMyPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyPageBinding.inflate(layoutInflater)

        binding.signUp.setOnClickListener {
            startActivity(Intent(requireContext(), SignUpActivity::class.java))
        }

        binding.login.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
        return binding.root
    }
}