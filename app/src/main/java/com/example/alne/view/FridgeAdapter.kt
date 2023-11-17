package com.example.alne.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alne.R
import com.example.alne.databinding.ItemFridgeBinding
import com.example.alne.model.Food

class FridgeAdapter(private val items: ArrayList<Food>): RecyclerView.Adapter<FridgeAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(food: Food)
    }
    private lateinit var mItemClickListener : MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    inner class ViewHolder(val binding: ItemFridgeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(food: Food){
            binding.itemFridgeTitleTv.text = food.name
            binding.itemFridgeExpireTv.text = food.expireDate
            binding.itemFridgeIv.setImageResource(R.drawable.bibimbap)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FridgeAdapter.ViewHolder {
        val binding = ItemFridgeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FridgeAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.binding.root.setOnClickListener{
            mItemClickListener.onItemClick(items[position])
        }

        holder.binding.itemFridgeDeleteIb.setOnClickListener{
            deleteFood(position)
        }
    }

    fun addFood(food: Food){
        items.add(food)
        notifyDataSetChanged()
    }

    fun deleteFood(position: Int){
        items.removeAt(position)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = items.size
}