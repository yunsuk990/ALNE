package com.example.alne.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alne.R
import com.example.alne.databinding.FragmentFridgeAllBinding
import com.example.alne.model.Food
import com.google.android.material.textfield.TextInputEditText


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
            add(Food("사과",0,null,null,"2023-12-20까지"))
            add(Food("바나나",0,null,null,"2023-11-20까지"))
            add(Food("당근",0,null,null,"2023-12-230까지"))
            add(Food("오이",0,null,null,"2023-11-26까지"))
            add(Food("오이",0,null,null,"2023-11-26까지"))
            add(Food("오이",0,null,null,"2023-11-26까지"))
            add(Food("오이",0,null,null,"2023-11-26까지"))
            add(Food("오이",0,null,null,"2023-11-26까지"))
        }
        fridgeadapter = FridgeAdapter(testData)
        binding.fridgeAllRv.adapter = fridgeadapter
        binding.fridgeAllRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false )
        fridgeadapter.setMyItemClickListener(object: FridgeAdapter.MyItemClickListener{
            override fun onItemClick(food: Food) {
                getCustomDialog(food)
            }

            override fun onInfoClick(view: View, position: Int) {
                val popupBase = view.findViewById<ImageButton>(R.id.item_fridge_delete_ib)
                val popupMenu = PopupMenu(context, popupBase)
                popupMenu.menuInflater.inflate(R.menu.food_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { m ->
                    when(m.itemId){
                        R.id.menu_modify -> getCustomDialog(fridgeadapter.items[position])
                        R.id.menu_delete -> {
                            fridgeadapter.items.removeAt(position)
                            fridgeadapter.notifyDataSetChanged()
                        }
                    }
                    false
                }
                popupMenu.show()
            }
        })

//        binding.fridgeAllFloatingBt.setOnClickListener {
//            getCustomDialog(null)
//        }
        return binding.root
    }

    private fun getCustomDialog(food: Food?){
        val myAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items)
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_foodadd,  null)
        val spinner = view.rootView.findViewById<Spinner>(R.id.spinner)
        spinner.adapter = myAdapter
        spinner.setSelection(0)
        if(food != null){
            view.rootView.findViewById<TextInputEditText>(R.id.food_title_et).setText(food.name)
            view.rootView.findViewById<TextInputEditText>(R.id.food_memo_tv).setText(food.memo)

        }

        val alertDialog = AlertDialog.Builder(context, R.style.Dialog_food)
            .setView(view)
            .create()
        view.rootView.findViewById<AppCompatButton>(R.id.cancel_bt).setOnClickListener {
            alertDialog.dismiss()
        }
        view.rootView.findViewById<AppCompatButton>(R.id.submit_bt).setOnClickListener {
            fridgeadapter.addFood(Food("사과",0,null,null,"2023-12-20까지"))
            alertDialog.dismiss()
        }
        alertDialog.show()
    }



}