package com.example.alne.view.Recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alne.databinding.FragmentClassBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ClassFragment : BottomSheetDialogFragment(){
    lateinit var binding: FragmentClassBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentClassBinding.inflate(layoutInflater)

        binding.classKoreanFoodBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classJapanFoodBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classChinaFoodBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classItalyFoodBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classAsianfoodBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classFishBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classStirFryBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classPizzaBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classSteamedBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classHotpotBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classSaladBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classNoodleBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classRiceCakeBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classSeasoningSauceBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classRiceBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classSoupBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classPancakeBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classBreadBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classRisottoBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classFriedFoodBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classLunchBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classKimchiBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classItalianBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classAfforestationBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classBeverageBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classBurgerBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classGrains1Bt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classVegeBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classMushroomBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classSeaweedBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classNutsBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classBeefBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classPigbeefBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classChickenBeefBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classProcessedFoodsBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classFlourBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classEggBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classFish1Bt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classBeanBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classEtcBt.setOnClickListener { it.isSelected = !it.isSelected }
        binding.classVegetablesBt.setOnClickListener { it.isSelected = !it.isSelected }


        return binding.root
    }
}