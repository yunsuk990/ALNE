package com.example.alne.view.Recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.alne.R
import com.example.alne.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecipeDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        binding.recipeDetailTb.bringToFront()
        setContentView(binding.root)

        binding.recipeDetailTb.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.share -> {
                        Log.d("share", item.toString())
                    }

                    R.id.like -> {
                        Log.d("like", item.toString())
                    }

                    R.id.bookmark -> {
                        Log.d("bookmark", item.toString())
                    }
                    else -> {
                        Log.d("finish", item.toString())
                    }
                }
                return true
            }
        })

        binding.recipeDetailTb.setNavigationOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                onBackPressed()
            }

        })
    }
}