package com.example.alne.view.Recipe.viewpage.review.viewpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alne.GlobalApplication
import com.example.alne.databinding.ItemReviewBinding
import com.example.alne.model.Comments
import com.example.alne.model.Review

class ReviewPageRVAdapter(var items: ArrayList<Comments>): RecyclerView.Adapter<ReviewPageRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        fun deleteComment(position: Int)
    }

    fun removeItem(position: Int){
        items.removeAt(position)
        notifyDataSetChanged()
    }

    private lateinit var myItemClickListener: MyItemClickListener

    fun setMyItemClickListener(myItemClickListener: MyItemClickListener){
        this.myItemClickListener = myItemClickListener
    }

    inner class ViewHolder(val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(comment: Comments){
            binding.itemReviewNickname.text = comment.user.name
            binding.itemReviewSummary.text = comment.detail
//            binding.itemReviewDate.text = review.date
            binding.itemReviewRatingbar.rating = comment.grade.toFloat()
            if(comment.user.id == GlobalApplication.prefManager.getUserToken()?.userId){
                binding.itemReviewInfo.visibility = View.VISIBLE
            }else{
                binding.itemReviewInfo.visibility = View.INVISIBLE
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.binding.itemReviewDelete.setOnClickListener {
            myItemClickListener.deleteComment(position)
        }
    }


}