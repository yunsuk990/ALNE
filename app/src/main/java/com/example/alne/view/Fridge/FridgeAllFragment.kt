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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alne.GlobalApplication
import com.example.alne.R
import com.example.alne.SharedPrefManager
import com.example.alne.databinding.FragmentFridgeAllBinding
import com.example.alne.model.Food
import com.example.alne.model.Jwt
import com.example.alne.model.UserId
import com.example.alne.viewmodel.FridgeViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch


class FridgeAllFragment : Fragment(), MyCustomDialogDetailInterface {
    lateinit var binding: FragmentFridgeAllBinding
    lateinit var viewModel: FridgeViewModel
    lateinit var fridgeadapter: FridgeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFridgeAllBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(FridgeViewModel::class.java)
        Log.d("FridgeAllFragment", "onCreateView")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFridgeLiveData.observe(viewLifecycleOwner, Observer { items ->
            Log.d("getFridge", items.toString())
            fridgeadapter = FridgeAdapter(requireContext(), items)
            binding.fridgeAllRv.adapter = fridgeadapter
            binding.fridgeAllRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false )
            fridgeadapter.setMyItemClickListener(object: FridgeAdapter.MyItemClickListener {
                override fun onItemClick(food: Food) {
                    getCustomDialog(food)
                }

                // 보유재료 삭제 기능
                override fun onInfoClick(view: View, position: Int) {
                    viewModel.deleteFridgeFood(UserId(
                        getUserToken()?.userId!!,
                        fridgeadapter.items[position].userId!!
                    ))
                }
            })
        })
    }

    private fun getCustomDialog(food: Food){
        CustomDialogDetail(requireContext(), getUserToken()!!,food, this).show(requireActivity().supportFragmentManager, "CustomDialog")
    }

    fun getUserToken() = GlobalApplication.prefManager.getUserToken()

    // 재료 수정하기(편집)
    override fun onSubmitBtnDetailClicked(food: Food) {
        Log.d("onSubmitBtnDetailClicked", "launch")
        viewModel.addFridgeData(getUserToken()?.accessToken!!, food)
    }

}