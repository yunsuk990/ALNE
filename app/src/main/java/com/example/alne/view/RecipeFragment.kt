package com.example.alne.view

import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alne.R
import com.example.alne.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {
    lateinit var binding: FragmentRecipeBinding
    var check: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(layoutInflater)


//        binding.recipeMenuAllLinear.setOnClickListener {
//            check = !check
//            if(check){
//                binding.recipeMenuAllLinear.setBackgroundResource(R.drawable.image_border2)
//            }else{
//                binding.recipeMenuAllLinear.setBackgroundResource(R.drawable.image_border)
//            }
//        }




        return binding.root
    }
}