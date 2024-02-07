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
import com.google.gson.Gson


class FridgeAllFragment : Fragment(), MyCustomDialogDetailInterface {
    lateinit var binding: FragmentFridgeAllBinding
    lateinit var viewModel: FridgeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFridgeAllBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(FridgeViewModel::class.java)

        Log.d("FridgeAllFragment", "onCreateView")
        if(getUserToken() != null){
            viewModel.getFridgeFood(getUserToken().accessToken!!, UserId(getUserToken().userId, null))
        }

        val fridgeadapter = FridgeAdapter(requireContext())
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
                            fridgeadapter.notifyDataSetChanged()
                            viewModel.deleteFridgeFood(UserId( getUserToken().userId,
                                fridgeadapter.items[position].userId!!
                            ))
                        }
                    }
                    false
                }
                popupMenu.show()
            }
        })

        viewModel.getFridgeLiveData.observe(viewLifecycleOwner, Observer { res ->
            Log.d("getFridge", res.toString())
            fridgeadapter.addAllFood(res)
        })

        viewModel.addFridgeLiveData.observe(viewLifecycleOwner, Observer { res ->
            if(res){
                //전체 재료 정보 업데이트
                viewModel.getFridgeFood(getUserToken().accessToken!!, UserId(getUserToken().userId, null))
            }
        })

        viewModel.delFridgeLiveData.observe(viewLifecycleOwner, Observer { res ->
            Log.d("delFridge", res.toString())
            if (res){
                viewModel.getFridgeFood(getUserToken().accessToken!!, UserId(getUserToken().userId, null))
            }
        })

        return binding.root
    }

    private fun getCustomDialog(food: Food){
        CustomDialogDetail(requireContext(),getUserToken(),food, this@FridgeAllFragment).show(requireActivity().supportFragmentManager, "CustomDialog")
    }

    fun getUserToken(): Jwt{
        val sharedPreferences = activity?.getSharedPreferences("user_info", AppCompatActivity.MODE_PRIVATE)
        val userJwt = Gson().fromJson(sharedPreferences?.getString("jwt",null), Jwt::class.java)
        Log.d("getjwt", userJwt.toString())
        return userJwt
    }


    // 재료 수정하기(편집)
    override fun onSubmitBtnDetailClicked(food: Food) {
        viewModel.addFridgeData(getUserToken().accessToken!!, food)
    }

}