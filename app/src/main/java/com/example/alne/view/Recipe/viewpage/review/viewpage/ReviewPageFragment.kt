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
import com.example.alne.GlobalApplication
import com.example.alne.databinding.FragmentReviewPageBinding
import com.example.alne.model.Comments
import com.example.alne.model.requestComment
import com.example.alne.room.model.recipe
import com.example.alne.view.Fridge.IngredientChoice
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
            var adapter = ReviewPageRVAdapter(it.comments as ArrayList<Comments>)
            adapter.setMyItemClickListener(object: ReviewPageRVAdapter.MyItemClickListener{
                override fun deleteComment(position: Int) {
                    viewModel.deleteUserComment(requestComment(GlobalApplication.prefManager.getUserToken()?.userId!!, recipe.recipe_code))
                    adapter.removeItem(position)
                }
            })
            binding.reviewPageRv.adapter = adapter
            binding.reviewPageRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        })

        binding.reviewPageReviewBt.setOnClickListener {
            Log.d("reviewPageReviewBt", "clicked")
            val dialog = UserReviewBottomSheetDialog()
            var bundle: Bundle = Bundle()
            bundle.putString("recipe", Gson().toJson(recipe))
            dialog.arguments = bundle
            dialog.show(requireActivity().supportFragmentManager, "")
        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }

}