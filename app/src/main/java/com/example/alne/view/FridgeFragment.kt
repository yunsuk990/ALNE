package com.example.alne.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alne.databinding.FragmentFridgeBinding
import com.google.android.material.tabs.TabLayoutMediator

class FridgeFragment : Fragment() {

    lateinit var binding: FragmentFridgeBinding
    private val information = arrayListOf("All", "냉장","냉동")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFridgeBinding.inflate(inflater, container, false)

        val fridgeAdapter = FridgeVPAdapter(this)
        binding.fridgeVp.adapter = fridgeAdapter
        TabLayoutMediator(binding.fridgeTl, binding.fridgeVp){ tab, position ->
            tab.text = information[position]
        }.attach()



        return binding.root
    }
}