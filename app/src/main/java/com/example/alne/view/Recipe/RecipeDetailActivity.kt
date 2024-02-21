package com.example.alne.view.Recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.alne.R
import com.example.alne.databinding.ActivityRecipeDetailBinding
import com.example.alne.model.Recipe
import com.example.alne.room.model.recipe
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class RecipeDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecipeDetailBinding
    private val information = arrayListOf("순서 및 후기", "재료","참고 영상")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        binding.recipeDetailTb.bringToFront()
        setContentView(binding.root)
        init()
        setOnClickListener()
    }
    private fun init(){
        var jsonRecipe = intent.getStringExtra("recipe")
        var recipe: recipe = Gson().fromJson(jsonRecipe, recipe::class.java)
        binding.recipeDetailTitleTv.text = recipe.name
        binding.recipeDetailChefTv.text = recipe.difficulty
        binding.recipeDetailIntroduceTv.text = recipe.introduce
        binding.recipeDetailCategoryTv1.text = "#" + recipe.classification
        binding.recipeDetailTimeTv.text = "약 " + recipe.time + "분"
        binding.recipeDetailKcalTv.text = recipe.calorie.toString() + "kcal"
        Glide.with(this@RecipeDetailActivity).load(recipe.imageurl).into(binding.recipeDetailFoodIv)

        val recipeAdapter = RecipeDetailVPAdapter(this@RecipeDetailActivity, recipe)
        binding.recipeDetailVp.adapter = recipeAdapter
        TabLayoutMediator(binding.recipeDetailTl, binding.recipeDetailVp){ tab, position ->
            tab.text = information[position]
        }.attach()
    }

    private fun setOnClickListener(){
        binding.recipeDetailTb.setNavigationOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                onBackPressed()
            }
        })
    }
}