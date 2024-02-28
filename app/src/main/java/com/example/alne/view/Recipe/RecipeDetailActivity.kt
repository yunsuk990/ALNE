package com.example.alne.view.Recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.alne.R
import com.example.alne.databinding.ActivityRecipeDetailBinding
import com.example.alne.model.DeleteFavorite
import com.example.alne.room.model.recipe
import com.example.alne.viewmodel.RecipeDetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class RecipeDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecipeDetailBinding
    lateinit var viewModel: RecipeDetailViewModel
    private val information = arrayListOf("순서 및 후기", "재료","참고 영상")
    var favorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recipeDetailTb.bringToFront()
        var recipe: recipe = Gson().fromJson(intent.getStringExtra("recipe"), recipe::class.java)
        Log.d("RecipeDetailActivity_recipe", recipe.toString())
        viewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)

        init(recipe)
        setOnClickListener()
    }

    private fun init(recipe: recipe){
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

        binding.like.setOnClickListener {
            if(favorite){
                binding.like.setImageResource(R.drawable.like_off)
                favorite = false
//                viewModel.deleteRecipeFavorite(DeleteFavorite)
            }else{
                binding.like.setImageResource(R.drawable.like_on)
                favorite = true
                viewModel.addRecipeFavorite(recipe.recipe_code)
            }
        }


    }

    private fun setOnClickListener(){
        binding.recipeDetailTb.setNavigationOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                onBackPressed()
            }
        })
    }
}