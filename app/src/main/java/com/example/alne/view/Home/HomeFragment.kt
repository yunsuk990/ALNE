package com.example.alne.view.Home

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alne.databinding.FragmentHomeBinding
import com.example.alne.model.Food

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)


        var textItem: ArrayList<Food> = ArrayList()


        var adapter = ExpireAdapter(textItem)
        binding.homeItemRv.adapter = adapter

        binding.homeSv.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { saveQuery(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return binding.root
    }

    fun saveQuery(item: String){
        val sharedPreferences = context?.getSharedPreferences("search_query", MODE_PRIVATE)
        val edit = sharedPreferences?.edit()
        edit?.putString("query",item)
        edit?.commit()
    }


}