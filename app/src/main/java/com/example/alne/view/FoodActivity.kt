package com.example.alne.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.alne.databinding.ActivityFoodBinding

class FoodActivity : AppCompatActivity() {

    lateinit var binding: ActivityFoodBinding
    val items = arrayListOf<String>("냉장", "냉동")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        binding.spinner.adapter = myAdapter
        binding.spinner.setSelection(0)


    }
}