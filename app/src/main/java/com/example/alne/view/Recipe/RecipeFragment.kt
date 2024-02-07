package com.example.alne.view.Recipe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alne.databinding.FragmentRecipeBinding
import com.example.alne.model.Recipe
import com.example.alne.viewmodel.RecipeViewModel
import com.google.gson.Gson

class RecipeFragment : Fragment() {
    lateinit var binding: FragmentRecipeBinding
    lateinit var viewModel: RecipeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        viewModel.getAllRecipe()

        binding.recipeMenuAllTv.requestFocus()

//        binding.recipeDjIb.setOnClickListener {
//            val bottomSheet = ClassFragment()
//            bottomSheet.show(requireActivity().supportFragmentManager, bottomSheet.tag )
//        }

        val gridAdapter = RecipeGVAdapter(requireContext())
        viewModel.getRecipeLiveData.observe(viewLifecycleOwner, Observer {data ->
            Log.d("viewModel" , data.toString())
            gridAdapter.addItems(data)
        })
        gridAdapter.setMyItemClickListener(object: RecipeGVAdapter.setOnClickListener{
            override fun clickItem(recipe: Recipe) {
                var intent = Intent(requireContext(), RecipeDetailActivity::class.java)
                intent.putExtra("recipe", Gson().toJson(recipe))
                startActivity(intent)
            }
        })
        binding.recipeGv.adapter = gridAdapter

        return binding.root
    }
}