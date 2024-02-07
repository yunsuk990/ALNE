package com.example.alne.view.Fridge

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.icu.util.Calendar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.alne.R
import com.example.alne.databinding.ItemFridgeBinding
import com.example.alne.model.Food
import java.text.SimpleDateFormat

class FridgeColdAdapter(val context: Context): RecyclerView.Adapter<FridgeColdAdapter.ViewHolder>() {

    val items: ArrayList<Food> = ArrayList()
    var calendar = Calendar.getInstance()
    init {
        Log.d("adapter", "2")
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND,0)
        calendar.set(Calendar.MILLISECONDS_IN_DAY,0)
    }

    interface MyItemClickListener {
        fun onItemClick(food: Food)
        fun onInfoClick(view: View, position: Int)
    }
    private lateinit var mItemClickListener : MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    inner class ViewHolder(val binding: ItemFridgeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(food: Food){
            var date = food.exp!!.split(" ")
            var sfp = SimpleDateFormat("yyyy.MM.dd")
            var da = sfp.parse(date[0])
            var diff: Long = ((da.time - calendar.time.time)/ (60 * 60 * 24 * 1000))

            // 유효날짜 - 등록 날짜
            var startExp = 7.0

            binding.itemFridgeTitleTv.text = food.name
            binding.itemFridgeExpireTv.text = date[0]+" 까지"
            binding.itemFridgeExpireInfoTv.text = "유효기간 ${diff}일 남음"
            binding.itemFridgeIv.setImageResource(R.drawable.bibimbap)
            if(food.storage == "FROZEN"){
                binding.itemFridgeStorageTv.text = "냉동"
            }else{
                binding.itemFridgeStorageTv.text = "냉장"
            }

            var progress: Double = (startExp - diff) / startExp
            var scale = progress*100
            Log.d("progress_cold", progress.toString())
            Log.d("diff_cold", diff.toString())
            Log.d("scale_cold", scale.toString())
            if(scale in 0.0..20.0){
                binding.itemFridgeExpireInfoTv.setTextColor(Color.parseColor("#00FF1A"))
                binding.itemFridgePb.progressDrawable = ContextCompat.getDrawable(context, R.drawable.progressbar_border_low)
                binding.itemFridgePb.max = startExp.toInt()
                binding.itemFridgePb.progress = (progress * startExp).toInt()
            }else if(scale in 21.0..50.0){
                binding.itemFridgeExpireInfoTv.setTextColor(Color.parseColor("#FFD500"))
                binding.itemFridgePb.progressDrawable = ContextCompat.getDrawable(context, R.drawable.progressbar_border_low_high)
                binding.itemFridgePb.max = startExp.toInt()
                binding.itemFridgePb.progress = (progress * startExp).toInt()
            }
            else if(scale in 51.0..80.0){
                binding.itemFridgeExpireInfoTv.setTextColor(Color.parseColor("#FF9900"))
                binding.itemFridgePb.progressDrawable = ContextCompat.getDrawable(context, R.drawable.progressbar_border_high_low)
                binding.itemFridgePb.max = startExp.toInt()
                binding.itemFridgePb.progress = (progress * startExp).toInt()
            }else{
                binding.itemFridgeExpireInfoTv.setTextColor(Color.RED)
                binding.itemFridgePb.progressDrawable = ContextCompat.getDrawable(context, R.drawable.progressbar_border_high)
                binding.itemFridgePb.max = startExp.toInt()
                binding.itemFridgePb.progress = (progress * startExp).toInt()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFridgeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.binding.root.setOnClickListener{
            mItemClickListener.onItemClick(items[position])
        }

        holder.binding.itemFridgeDeleteIb.setOnClickListener{
            mItemClickListener.onInfoClick(it,position)
        }
    }

    fun addAllFood(item: ArrayList<Food>){
        items.clear()
        for(food in item){
            if(food.storage == "COLD"){
                items.add(food)
            }
        }
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = items.size
}