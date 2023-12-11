package com.example.alne.view.Recipe.viewpage.ingredient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alne.databinding.FragmentIngredientBinding
import com.example.alne.model.Ingredient


class IngredientFragment : Fragment() {

    lateinit var binding: FragmentIngredientBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentIngredientBinding.inflate(layoutInflater)

        val items: ArrayList<Ingredient> = ArrayList()
        items.add(Ingredient(1, "당근","123"))
        items.add(Ingredient(1, "파","123"))
        items.add(Ingredient(1, "바나나","123"))
        items.add(Ingredient(1, "당근","123"))
        items.add(Ingredient(1, "당근","123"))
        items.add(Ingredient(1, "당근","123"))

        binding.ingredientRv.adapter = IngredientRVAdapter(items)
        binding.ingredientRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.ingredientRv.addItemDecoration(DividerItemDecoration(context, 1))


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }

}