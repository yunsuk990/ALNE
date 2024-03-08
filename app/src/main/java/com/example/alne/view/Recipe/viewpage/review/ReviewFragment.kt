package com.example.alne.view.Recipe.viewpage.review

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alne.R
import com.example.alne.databinding.FragmentReviewBinding
import com.example.alne.model.Process
import com.example.alne.room.model.recipe
import com.example.alne.viewmodel.RecipeDetailViewModel
import com.google.android.material.tabs.TabLayoutMediator

class ReviewFragment(val recipe: recipe): Fragment() {
    lateinit var binding: FragmentReviewBinding
    lateinit var reviewAdapter: ReviewVPAdapter
    lateinit var viewModel: RecipeDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentReviewBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity()).get(RecipeDetailViewModel::class.java)
        Log.d("ReviewFragment", recipe.toString())

        var starView = layoutInflater.inflate(R.layout.layout_tab_star, null)
        var reviewView = layoutInflater.inflate(R.layout.layout_tab_review, null)
        var list = arrayListOf(reviewView, starView)
        binding.recipeReviewTl.addTab(binding.recipeReviewTl.newTab().setCustomView(starView))
        binding.recipeReviewTl.addTab(binding.recipeReviewTl.newTab().setCustomView(reviewView))
        reviewAdapter = ReviewVPAdapter(this, recipe)
        binding.recipeReviewVp.adapter = reviewAdapter
        TabLayoutMediator(binding.recipeReviewTl, binding.recipeReviewVp){
                tap, position ->
            tap.customView = list[position]
        }.attach()


        viewModel.getRecipeProcessLiveData.observe(viewLifecycleOwner, Observer { recipeProcess ->
            binding.recipeReviewRv.adapter = ReviewRVAdapter(recipeProcess.recipeProcess as ArrayList<Process>)
            binding.recipeReviewRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            reviewView.findViewById<TextView>(R.id.recipe_review_tv).text = recipeProcess.comments.size.toString()
            starView.findViewById<TextView>(R.id.recipe_star_title).text = "평점"
            starView.findViewById<TextView>(R.id.recipe_star_tv).text = recipeProcess.gradeDto.average.toString()
        })


        return binding.root
    }
}