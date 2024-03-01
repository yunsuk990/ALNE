package com.example.alne.view.Recipe.viewpage.review.viewpage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alne.databinding.FragmentReviewPageBinding
import com.example.alne.room.model.recipe
import com.example.alne.viewmodel.RecipeDetailViewModel
import com.google.gson.Gson

class ReviewPageFragment(val recipe: recipe) : Fragment() {

    lateinit var binding: FragmentReviewPageBinding
    lateinit var viewModel: RecipeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReviewPageBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity()).get(RecipeDetailViewModel::class.java)
        Log.d("ReviewPageFragment", "onCreateView")


        viewModel.getRecipeProcessLiveData.observe(viewLifecycleOwner, Observer{
            binding.reviewPageRv.adapter = ReviewPageRVAdapter(it.comments)
            binding.reviewPageRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        })

        binding.reviewPageReviewBt.setOnClickListener {
            var intent = Intent(context, UserReviewActivity::class.java)
            intent.putExtra("recipe", Gson().toJson(recipe))
            startActivity(intent)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }

}