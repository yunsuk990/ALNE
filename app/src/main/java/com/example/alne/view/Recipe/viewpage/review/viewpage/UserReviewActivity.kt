package com.example.alne.view.Recipe.viewpage.review.viewpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.alne.GlobalApplication
import com.example.alne.databinding.ActivityUserReviewBinding
import com.example.alne.model.Comment
import com.example.alne.room.model.recipe
import com.example.alne.viewmodel.RecipeDetailViewModel
import com.google.gson.Gson

class UserReviewActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserReviewBinding
    lateinit var viewModel: RecipeDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this@UserReviewActivity).get(RecipeDetailViewModel::class.java)

        var recipe = Gson().fromJson(intent.getStringExtra("recipe"), recipe::class.java)
        init(recipe)

        binding.userReviewTb.setNavigationOnClickListener { onBackPressed() }

        binding.userReviewSubmitTv.setOnClickListener {
            var userId = GlobalApplication.prefManager.getUserToken()!!.userId
            Log.d("userId", userId.toString())
            Log.d("editText", binding.userReviewCommentEt.text.toString())
            // 댓글 정보 서버로 보내기
            viewModel.addUserComment(Comment(recipe.recipe_code, userId, binding.userReviewCommentEt.text.toString(), binding.baseRatingBar.rating.toInt(), "url"))
        }

        viewModel.addUserCommentLiveData.observe(this, Observer { it ->
            if(!it){
                Toast.makeText(this, "댓글 작성에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
    }

    private fun init(recipe: recipe?){
        Glide.with(this).load(recipe?.imageurl).into(binding.userReviewMainFoodIv)
        binding.userReviewMainFoodTv.text = recipe?.name
        binding.userReviewMainFoodTimeTv.text = "약 " + recipe?.time.toString() + "분"
        binding.userReviewMainFoodChefTv.text = recipe?.difficulty
        binding.userReviewMainFoodKcalTv.text = recipe?.calorie.toString()+"kcal"

    }
}