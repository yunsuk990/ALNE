package com.example.alne.view.Recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.alne.R
import com.example.alne.model.Recipe

class RecipeGVAdapter(val context: Context, val items: ArrayList<Recipe>): BaseAdapter() {



    interface setOnClickListener {
        fun clickItem(recipe: Recipe)
    }

    private lateinit var myItemClickListener: setOnClickListener

    fun setMyItemClickListener(itemClickListener: setOnClickListener){
        myItemClickListener = itemClickListener
    }

    override fun getCount(): Int = items.size

    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recipe, null)
        view.findViewById<TextView>(R.id.item_recipe_title_tv).text = items[p0].name
        view.findViewById<TextView>(R.id.item_recipe_time_tv).text = items[p0].time
        view.findViewById<TextView>(R.id.item_recipe_rank_tv).text = items[p0].difficulty
        view.setOnClickListener{
            myItemClickListener.clickItem(items[p0])
        }
//        binding.itemRecipeIv.setImageResource(items[p0].imageURL)
        return view
    }
}