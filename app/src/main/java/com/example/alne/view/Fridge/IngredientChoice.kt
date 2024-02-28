package com.example.alne.view.Fridge

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alne.GlobalApplication
import com.example.alne.R
import com.example.alne.databinding.IngredientchoiceBinding
import com.example.alne.room.model.food
import com.example.alne.viewmodelprivate.IngredientChoiceViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class IngredientChoice: BottomSheetDialogFragment() {

    lateinit var binding: IngredientchoiceBinding
    lateinit var viewModel: IngredientChoiceViewModel
    private var listener: OnSendFromBottomSheetDialog? = null
    var adapter: IngredientChoiceAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = IngredientchoiceBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(IngredientChoiceViewModel::class.java)

        viewModel.getFridgeLiveData.observe(viewLifecycleOwner, Observer { items ->
            adapter = IngredientChoiceAdapter(items)
            adapter!!.setMyItemClickListener(object: IngredientChoiceAdapter.MyItemClickListener{
                override fun onItemClick(name: String) {
                    listener?.sendValue(name)
                    dismiss()
                }
            })
            binding.ingredientChoiceRv.adapter = adapter
            binding.ingredientChoiceRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        })

        binding.ingredientChoiceSv.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return binding.root
    }

    interface OnSendFromBottomSheetDialog {
        fun sendValue(value: String)
    }

    fun setCallback(listener: OnSendFromBottomSheetDialog) {
        this.listener = listener
    }

//    override fun getFilter(): Filter {
//        var filter = object: Filter(){
//            override fun performFiltering(p0: CharSequence?): FilterResults {
//
//            }
//
//            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
//
//            }
//        }
//    }
}