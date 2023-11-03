package com.example.alne

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alne.databinding.ActivityMainBinding
import com.example.alne.view.FridgeFragment
import com.example.alne.view.HomeFragment
import com.example.alne.view.MyPageFragment
import com.example.alne.view.RecipeFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()


    }

    fun initBottomNavigation(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, HomeFragment())
            .commitAllowingStateLoss()

            binding.bottomNavigationView.setOnItemSelectedListener{ item ->
                when(item.itemId){
                    R.id.homeFragment -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_container, HomeFragment())
                            .commitAllowingStateLoss()
                        return@setOnItemSelectedListener true
                    }

                    R.id.fridgeFragment -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_container, FridgeFragment())
                            .commitAllowingStateLoss()
                        return@setOnItemSelectedListener true
                    }

                    R.id.recipeFragment -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_container, RecipeFragment())
                            .commitAllowingStateLoss()
                        return@setOnItemSelectedListener true
                    }

                    R.id.myPageFragment -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_container, MyPageFragment())
                            .commitAllowingStateLoss()
                        return@setOnItemSelectedListener true
                    }

                }
                false
            }
    }
}