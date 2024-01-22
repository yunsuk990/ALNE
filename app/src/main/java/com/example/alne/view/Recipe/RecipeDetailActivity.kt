package com.example.alne.view.Recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.alne.R
import com.example.alne.databinding.ActivityRecipeDetailBinding
import com.example.alne.model.Recipe
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
        var recipe: Recipe = Gson().fromJson(intent.getStringExtra("recipe"), Recipe::class.java)
        binding.recipeDetailTitleTv.text = recipe.name
        binding.recipeDetailChefTv.text = recipe.difficulty
        binding.recipeDetailTimeTv.text = "약 " + recipe.time

        val recipeAdapter = RecipeDetailVPAdapter(this@RecipeDetailActivity)
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