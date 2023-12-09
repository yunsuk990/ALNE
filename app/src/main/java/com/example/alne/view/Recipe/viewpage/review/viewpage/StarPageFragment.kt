package com.example.alne.view.Recipe.viewpage.review.viewpage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alne.databinding.FragmentStarPageBinding

class StarPageFragment : Fragment() {

    lateinit var binding: FragmentStarPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.d("starPageFragment", "onCreateView")
        binding = FragmentStarPageBinding.inflate(layoutInflater)
        return binding.root
    }



}