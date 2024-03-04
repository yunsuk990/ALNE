package com.example.alne.view.Recipe.viewpage.review.viewpage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.alne.GlobalApplication
import com.example.alne.databinding.UserReviewDialogBinding
import com.example.alne.model.Comment
import com.example.alne.room.model.recipe
import com.example.alne.viewmodel.RecipeDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson

class UserReviewBottomSheetDialog: BottomSheetDialogFragment() {

    lateinit var binding: UserReviewDialogBinding
    lateinit var viewModel: RecipeDetailViewModel
    private var listener: OnSendFromBottomSheetDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserReviewDialogBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity()).get(RecipeDetailViewModel::class.java)

        var recipe = Gson().fromJson(arguments?.getString("recipe"), recipe::class.java)
        init(recipe)


        binding.userReviewCancelBt.setOnClickListener {
            dismiss()
        }

        binding.userReviewSubmitBt.setOnClickListener {
            var userId = GlobalApplication.prefManager.getUserToken()!!.userId
            Log.d("userId", userId.toString())
            Log.d("editText", binding.userReviewCommentEt.text.toString())
            // 댓글 정보 서버로 보내기
            viewModel.addUserComment(Comment(recipe.recipe_code, userId, binding.userReviewCommentEt.text.toString(), binding.baseRatingBar.rating.toInt(), "url"))
        }

        viewModel.addUserCommentLiveData.observe(this, Observer { it ->
            if(!it){
                Toast.makeText(requireContext(), "리뷰 작성에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.getRecipeProcess(recipe.recipe_code)
            }
            dismiss()
        })


        return binding.root
    }

    private fun init(recipe: recipe?){
        Glide.with(this).load(recipe?.imageurl).into(binding.userReviewMainFoodIv)
    }

    interface OnSendFromBottomSheetDialog {
        fun sendValue(value: String)
    }

    fun setCallback(listener: OnSendFromBottomSheetDialog) {
        this.listener = listener
    }
}