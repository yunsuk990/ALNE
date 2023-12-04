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
import com.example.alne.model.Recipe

class RecipeFragment : Fragment() {
    lateinit var binding: FragmentRecipeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(layoutInflater)
        binding.recipeMenuAllTv.requestFocus()

        val items = ArrayList<Recipe>()
        items.add(Recipe("라면","--","라면","일식","40분","40분","보통","11"))
        items.add(Recipe("라면","--","라면","일식","40분","40분","보통","11"))
        items.add(Recipe("라면","--","라면","일식","40분","40분","보통","11"))
        items.add(Recipe("라면","--","라면","일식","40분","40분","보통","11"))
        items.add(Recipe("라면","--","라면","일식","40분","40분","보통","11"))
        items.add(Recipe("라면","--","라면","일식","40분","40분","보통","11"))
        val gridAdapter = RecipeGVAdapter(requireContext(),items)
        binding.recipeGv.adapter = gridAdapter
        return binding.root
    }
}