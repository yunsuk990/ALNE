package com.example.alne.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alne.R
import com.example.alne.databinding.FragmentFridgeAllBinding
import com.example.alne.model.Food

class FridgeAllFragment : Fragment() {
    lateinit var binding: FragmentFridgeAllBinding
    val items = arrayListOf<String>("냉장", "냉동")
    lateinit var fridgeadapter: FridgeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFridgeAllBinding.inflate(inflater, container, false)


        var testData = ArrayList<Food>().apply {
            add(Food("사과",0,"2023-12-20까지"))
            add(Food("바나나",0,"2023-11-20까지"))
            add(Food("당근",0,"2023-12-230까지"))
            add(Food("오이",0,"2023-11-26까지"))
            add(Food("오이",0,"2023-11-26까지"))
            add(Food("오이",0,"2023-11-26까지"))
            add(Food("오이",0,"2023-11-26까지"))
            add(Food("오이",0,"2023-11-26까지"))
        }
        fridgeadapter = FridgeAdapter(testData)
        fridgeadapter.setMyItemClickListener(object: FridgeAdapter.MyItemClickListener{
            override fun onItemClick(food: Food) {
                startActivity(Intent(context,FoodActivity::class.java))
            }
        })
        binding.fridgeAllRv.adapter = fridgeadapter
        binding.fridgeAllRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false )

        binding.fridgeAllFloatingBt.setOnClickListener {
            getCustomDialog()
        }
        return binding.root
    }

    private fun getCustomDialog(){
        val myAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items)
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_foodadd,  null)
        val spinner = view.rootView.findViewById<Spinner>(R.id.spinner)
        spinner.adapter = myAdapter
        spinner.setSelection(0)
        val alertDialog = AlertDialog.Builder(context, R.style.Dialog_food)
            .setView(view)
            .create()
        view.rootView.findViewById<AppCompatButton>(R.id.cancel_bt).setOnClickListener {
            alertDialog.dismiss()
        }
        view.rootView.findViewById<AppCompatButton>(R.id.submit_bt).setOnClickListener {
            fridgeadapter.addFood(Food("사과",0,"2023-12-20까지"))
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
}