package com.example.alne.view.Recipe.viewpage.review.viewpage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alne.databinding.ItemReviewBinding
import com.example.alne.model.Review

class ReviewPageRVAdapter(val items: ArrayList<Review>): RecyclerView.Adapter<ReviewPageRVAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(review: Review){
            binding.itemReviewNickname.text = review.nickname
            binding.itemReviewSummary.text = review.text
            binding.itemReviewDate.text = review.date
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


}