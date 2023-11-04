package com.example.alne.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.alne.R
import com.example.alne.databinding.FragmentHomeBinding
import com.example.alne.model.Food

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)


        var textItem: ArrayList<Food> = ArrayList()
        textItem.add(Food("바나나",R.drawable.food.toString(),"2022.12.21"))
        textItem.add(Food("오렌지",R.drawable.food.toString(),"2022.12.21"))
        textItem.add(Food("사과",R.drawable.food.toString(),"2022.12.21"))
        textItem.add(Food("옥수수",R.drawable.food.toString(),"2022.12.21"))
        textItem.add(Food("옥수수",R.drawable.food.toString(),"2022.12.21"))
        textItem.add(Food("옥수수",R.drawable.food.toString(),"2022.12.21"))
        textItem.add(Food("옥수수",R.drawable.food.toString(),"2022.12.21"))
        textItem.add(Food("옥수수",R.drawable.food.toString(),"2022.12.21"))
        textItem.add(Food("옥수수",R.drawable.food.toString(),"2022.12.21"))
        textItem.add(Food("옥수수",R.drawable.food.toString(),"2022.12.21"))
        var adapter = ExpireAdapter(textItem)
        binding.homeItemRv.adapter = adapter




        return binding.root
    }


}