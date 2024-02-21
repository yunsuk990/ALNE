package com.example.alne.view.Fridge

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = IngredientchoiceBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(IngredientChoiceViewModel::class.java)

        viewModel.getFridgeLiveData.observe(viewLifecycleOwner, Observer { items ->
            val adapter = IngredientChoiceAdapter(items)
            adapter.setMyItemClickListener(object: IngredientChoiceAdapter.MyItemClickListener{
                override fun onItemClick(name: String) {
                    listener?.sendValue(name)
                    dismiss()
                }
            })
            binding.ingredientChoiceRv.adapter = adapter
            binding.ingredientChoiceRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        })

        return binding.root
    }

    interface OnSendFromBottomSheetDialog {
        fun sendValue(value: String)
    }

    fun setCallback(listener: OnSendFromBottomSheetDialog) {
        this.listener = listener
    }
}