package com.example.alne.view.Fridge

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alne.R
import com.example.alne.databinding.FragmentFridgeAllBinding
import com.example.alne.model.Food
import com.example.alne.model.Jwt
import com.example.alne.model.UserId
import com.example.alne.viewmodel.FridgeViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import retrofit2.Retrofit


class FridgeAllFragment : Fragment(), MyCustomDialogDetailInterface {
    lateinit var binding: FragmentFridgeAllBinding
    lateinit var fridgeadapter: FridgeAdapter
    lateinit var viewModel: FridgeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFridgeAllBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(FridgeViewModel::class.java)

        if(getUserToken() != null){
            viewModel.getFridgeFood(getUserToken().accessToken!!, UserId(getUserToken().userId))
        }

        fridgeadapter = FridgeAdapter()
        binding.fridgeAllRv.adapter = fridgeadapter
        binding.fridgeAllRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false )
        fridgeadapter.setMyItemClickListener(object: FridgeAdapter.MyItemClickListener {
            override fun onItemClick(food: Food) {
                getCustomDialog(food)
            }

            // 보유재료 삭제 기능
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

        viewModel.getFridgeLiveData.observe(viewLifecycleOwner, Observer { res ->
            fridgeadapter.addAllFood(res)
        })
        return binding.root
    }

    private fun getCustomDialog(food: Food){
        CustomDialogDetail(requireContext(),food, this@FridgeAllFragment).show(requireActivity().supportFragmentManager, "CustomDialog")
//        if(food != null){
//            view.rootView.findViewById<TextInputEditText>(R.id.food_title_et).setText(food.name)
//            view.rootView.findViewById<TextInputEditText>(R.id.food_memo_tv).setText(food.memo)
//
//        }
    }

    fun getUserToken(): Jwt{
        val sharedPreferences = activity?.getSharedPreferences("user_info", AppCompatActivity.MODE_PRIVATE)
        val userJwt = Gson().fromJson(sharedPreferences?.getString("jwt",null), Jwt::class.java)
        Log.d("getjwt", userJwt.toString())
        return userJwt
    }

    override fun onSubmitBtnClicked(food: Food) {

    }
}