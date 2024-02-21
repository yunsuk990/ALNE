package com.example.alne.view.Fridge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alne.databinding.ItemChoiceIngredientBinding
import com.example.alne.room.model.food

class IngredientChoiceAdapter(var items: ArrayList<food>): RecyclerView.Adapter<IngredientChoiceAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemChoiceIngredientBinding): RecyclerView.ViewHolder(binding.root){}

    interface MyItemClickListener {
        fun onItemClick(name: String)
    }
    private lateinit var mItemClickListener : MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): IngredientChoiceAdapter.ViewHolder {
        val binding = ItemChoiceIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemChoiceIngredientTv.text = items[position].name
        holder.binding.root.setOnClickListener {
            mItemClickListener.onItemClick(items[position].name)
        }
    }

    override fun getItemCount(): Int = items.size


}