package com.example.alne.view.Recipe.viewpage.review.viewpage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alne.databinding.FragmentReviewPageBinding
import com.example.alne.model.Review
import com.example.alne.room.model.recipe
import com.google.gson.Gson

class ReviewPageFragment(val recipe: recipe) : Fragment() {

    lateinit var binding: FragmentReviewPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReviewPageBinding.inflate(layoutInflater)
        Log.d("ReviewPageFragment", "onCreateView")
        val item = ArrayList<Review>()
        item.add(Review("최윤석","맛있습니다","2023.11.7"))
        item.add(Review("전승규","맛있습니다","2023.11.8"))
        item.add(Review("노영권","맛있습니다","2023.11.9"))
        item.add(Review("신해철","맛있습니다","2023.11.10"))
        binding.reviewPageRv.adapter = ReviewPageRVAdapter(item)
        binding.reviewPageRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        binding.reviewPageReviewBt.setOnClickListener {
            var intent = Intent(context, UserReviewActivity::class.java)
            intent.putExtra("recipe", Gson().toJson(recipe))
            startActivity(intent)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }

}