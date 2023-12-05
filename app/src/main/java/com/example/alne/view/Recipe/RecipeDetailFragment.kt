package com.example.alne.view.Recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alne.databinding.FragmentRecipeDetailBinding

class RecipeDetailFragment : Fragment() {

    lateinit var binding: FragmentRecipeDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeDetailBinding.inflate(layoutInflater)


        return binding.root
    }


}
