package com.example.alne.view.Recipe.viewpage.review

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alne.R
import com.example.alne.databinding.FragmentReviewBinding
import com.example.alne.model.Order
import com.google.android.material.tabs.TabLayoutMediator

class ReviewFragment : Fragment() {
    lateinit var binding: FragmentReviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentReviewBinding.inflate(layoutInflater)

        var starView = layoutInflater.inflate(R.layout.layout_tab_star, null)
        var reviewView = layoutInflater.inflate(R.layout.layout_tab_review, null)
        var list = arrayListOf(starView, reviewView)
        starView.findViewById<TextView>(R.id.recipe_star_title).text = "평점"
        binding.recipeReviewTl.addTab(binding.recipeReviewTl.newTab().setCustomView(starView))
        binding.recipeReviewTl.addTab(binding.recipeReviewTl.newTab().setCustomView(reviewView))
        binding.recipeReviewVp.adapter = ReviewVPAdapter(this)
        TabLayoutMediator(binding.recipeReviewTl, binding.recipeReviewVp){
                tap, position ->
            tap.customView = list[position]
        }.attach()


        val order = Order(1, arrayListOf("가는 파는 다듬어 씻은 뒤 4cm 길이로 썰고 양파는 채썬다.","돼지고기는 5cm 길이로 채썰어 청주와 간장에 버무린다.","굵은 파는 굵게 다지고 마늘, 생강은 곱게 다진다.",
            "식용유를 두른 프라이팬에 다진 굵은파, 다진 마늘, 생강을 볶은 뒤 간장을 넣는다.", "돼지고기를 볶다가 양파를 넣는다.", "가는파를 넣어 살짝 볶은 뒤 소금, 후춧가루, 참기름을 넣는다."))
        val items: ArrayList<String> = order.detail
        binding.recipeReviewRv.adapter = ReviewRVAdapter(items)
        binding.recipeReviewRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
}