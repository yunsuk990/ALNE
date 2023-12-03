package com.example.alne.view

import android.R
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import com.example.alne.databinding.FragmentFridgeBinding
import com.example.alne.model.Food
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText

class FridgeFragment : Fragment() {

    lateinit var binding: FragmentFridgeBinding
    private val information = arrayListOf("All", "냉장","냉동")
    val items = arrayListOf<String>("냉장", "냉동")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.d("FridgeFragment", "onCreateView()")
        // Inflate the layout for this fragment
        binding = FragmentFridgeBinding.inflate(inflater, container, false)

        val fridgeAdapter = FridgeVPAdapter(this)
        binding.fridgeVp.adapter = fridgeAdapter
        TabLayoutMediator(binding.fridgeTl, binding.fridgeVp){ tab, position ->
            tab.text = information[position]
        }.attach()


        binding.fridgeFloatingBt.setOnClickListener{
            getCustomDialog(null)
        }



        return binding.root
    }

    private fun getCustomDialog(food: Food?){
        val myAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item, items)
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(com.example.alne.R.layout.item_foodadd,  null)
        val spinner = view.rootView.findViewById<Spinner>(com.example.alne.R.id.spinner)
        spinner.adapter = myAdapter
        spinner.setSelection(0)
        if(food != null){
            view.rootView.findViewById<TextInputEditText>(com.example.alne.R.id.food_title_et).setText(food.name)
            view.rootView.findViewById<TextInputEditText>(com.example.alne.R.id.food_memo_tv).setText(food.memo)

        }

        val alertDialog = AlertDialog.Builder(context, com.example.alne.R.style.Dialog_food)
            .setView(view)
            .create()
        view.rootView.findViewById<AppCompatButton>(com.example.alne.R.id.cancel_bt).setOnClickListener {
            alertDialog.dismiss()
        }
        view.rootView.findViewById<AppCompatButton>(com.example.alne.R.id.submit_bt).setOnClickListener {
//            fridgeadapter.addFood(Food("사과",0,null,null,"2023-12-20까지"))
            alertDialog.dismiss()
        }
        alertDialog.show()
    }


    override fun onStart() {
        Log.d("FridgeFragment", "onStart()")
        super.onStart()
    }
    override fun onPause() {
        Log.d("FridgeFragment", "onPause()")
        super.onPause()
    }

    override fun onDestroyView() {
        Log.d("FridgeFragment", "onDestroyView()")
        super.onDestroyView()
    }

//    override fun onItemClick(food: Food) {
//        getCustomDialog(food)
//    }
}