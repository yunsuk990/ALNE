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
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.Year

class FridgeAdapter(val context: Context,  val items: ArrayList<Food>): RecyclerView.Adapter<FridgeAdapter.ViewHolder>() {


    var calendar = Calendar.getInstance()
    init {
        Log.d("adapter", "create")
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
            var diff = ((da.time - calendar.time.time))/ (60 * 60 * 24 * 1000)
            Log.d("diff", diff.toString())

            var startd = food.addDate!!.split(" ")
            var startDate = sfp.parse(startd[0])

            // 유효날짜 - 등록 날짜
            var startExp = (da.time - startDate.time)/ (60 * 60 * 24 * 1000)

            binding.itemFridgeTitleTv.text = food.name
            binding.itemFridgeExpireTv.text = date[0]+" 까지"
            binding.itemFridgeExpireInfoTv.text = "유효기간 ${diff}일 남음"
            binding.itemFridgeIv.setImageResource(R.drawable.camera)


            if(food.storage == "FROZEN"){
                binding.itemFridgeStorageTv.text = "냉동"
            }else{
                binding.itemFridgeStorageTv.text = "냉장"
            }

            var progress: Long = 0
            var scale = 0.0
            try{
                progress = (startExp - diff) / startExp
            }catch (e: Exception){
                if(startExp.toInt() == 0){
                    startExp = 100
                    progress = 100
                    scale = 100.0
                }
            }
            scale = ((progress*100).toDouble())


            Log.d("fridge:startExp", startExp.toString())
            Log.d("fridge:progress", progress.toString())
            Log.d("fridge:diff", diff.toString())
            Log.d("fridge:scale", scale.toString())
            if(diff > 15){
                binding.itemFridgeExpireInfoTv.setTextColor(Color.parseColor("#00FF1A"))
                binding.itemFridgePb.progressDrawable = ContextCompat.getDrawable(context, R.drawable.progressbar_border_low)
                binding.itemFridgePb.max = startExp.toInt()
                binding.itemFridgePb.progress = (progress * startExp).toInt()
            }else if(diff in 8..14){
                binding.itemFridgeExpireInfoTv.setTextColor(Color.parseColor("#FFD500"))
                binding.itemFridgePb.progressDrawable = ContextCompat.getDrawable(context, R.drawable.progressbar_border_low_high)
                binding.itemFridgePb.max = startExp.toInt()
                binding.itemFridgePb.progress = (progress * startExp).toInt()
            }
            else if(diff in 4..7){
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
        items.addAll(item)
        notifyDataSetChanged()
    }

    fun addFood(item: Food){
        items.add(items.size-1, item)
        Log.d("adapter:item.size", items.size.toString())
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = items.size
}