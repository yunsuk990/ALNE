package com.example.alne.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alne.databinding.ItemFridgeBinding
import com.example.alne.model.Food

class FridgeAdapter(private val items: ArrayList<Food>): RecyclerView.Adapter<FridgeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFridgeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(food: Food){
            binding.itemFridgeTitleTv.text = food.name
            binding.itemFridgeExpireTv.text = food.expireDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FridgeAdapter.ViewHolder {
        val binding = ItemFridgeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FridgeAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}