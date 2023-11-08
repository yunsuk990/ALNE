package com.example.alne.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alne.databinding.FragmentFridgeAllBinding
import com.example.alne.model.Food

class FridgeAllFragment : Fragment() {
    lateinit var binding: FragmentFridgeAllBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFridgeAllBinding.inflate(inflater, container, false)


        var testData = ArrayList<Food>().apply {
            add(Food("사과",0,"2023-12-20까지"))
            add(Food("바나나",0,"2023-11-20까지"))
            add(Food("당근",0,"2023-12-230까지"))
            add(Food("오이",0,"2023-11-26까지"))
            add(Food("오이",0,"2023-11-26까지"))
            add(Food("오이",0,"2023-11-26까지"))
            add(Food("오이",0,"2023-11-26까지"))
            add(Food("오이",0,"2023-11-26까지"))
        }
        val adapter = FridgeAdapter(testData)
        binding.fridgeAllRv.adapter = adapter
        binding.fridgeAllRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false )

        return binding.root
    }
}