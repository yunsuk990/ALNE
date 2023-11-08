package com.example.alne.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alne.databinding.FragmentFridgeFreezeBinding

class FridgeFreezeFragment : Fragment() {

    lateinit var binding: FragmentFridgeFreezeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFridgeFreezeBinding.inflate(inflater, container, false)
        return binding.root
    }

}