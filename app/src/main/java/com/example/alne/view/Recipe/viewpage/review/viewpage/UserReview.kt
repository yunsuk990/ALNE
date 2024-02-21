package com.example.alne.view.Recipe.viewpage.review.viewpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.alne.databinding.ActivityUserReviewBinding
import com.example.alne.room.model.recipe
import com.google.gson.Gson

class UserReview : AppCompatActivity() {

    lateinit var binding: ActivityUserReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var recipe = Gson().fromJson(intent.getStringExtra("recipe"), recipe::class.java)
        init(recipe)

        binding.userReviewTb.setNavigationOnClickListener { onBackPressed() }

        binding.userReviewSubmitTv.setOnClickListener {
            // 댓글 정보 서버로 보내기

            finish()
        }
    }

    private fun init(recipe: recipe?){
        Glide.with(this).load(recipe?.imageurl).into(binding.userReviewMainFoodIv)
        binding.userReviewMainFoodTv.text = recipe?.name
        binding.userReviewMainFoodTimeTv.text = "약 " + recipe?.time.toString() + "분"
        binding.userReviewMainFoodChefTv.text = recipe?.difficulty
        binding.userReviewMainFoodKcalTv.text = recipe?.calorie.toString()+"kcal"

    }
}