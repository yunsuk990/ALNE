package com.example.alne.view.Recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alne.databinding.FragmentRecipeBinding
import com.example.alne.room.model.recipe
import com.example.alne.viewmodel.RecipeViewModel
import com.google.gson.Gson

class RecipeFragment : Fragment() {
    lateinit var binding: FragmentRecipeBinding
    lateinit var viewModel: RecipeViewModel
    var items: ArrayList<recipe> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        binding.recipeMenuAllBt.requestFocus()

        binding.root.setOnClickListener{
            hideKeyBoard()
        }

        val gridAdapter = RecipeGVAdapter(requireContext())
        gridAdapter.setMyItemClickListener(object: RecipeGVAdapter.setOnClickListener{
            override fun clickItem(recipe: recipe) {
                var intent = Intent(requireContext(), RecipeDetailActivity::class.java)
                intent.putExtra("recipe", Gson().toJson(recipe))
                startActivity(intent)
            }
        })
        binding.recipeGv.adapter = gridAdapter

        viewModel.getRecipeLiveData.observe(viewLifecycleOwner, Observer {data ->
            Log.d("viewModel" , data.toString())
            items.addAll(data)
            gridAdapter.addItems(items)
        })

        binding.run {
            recipeMenuAllBt.setOnFocusChangeListener { view, b ->
                Log.d("recipeMenuAllBt_focus_changed" , b.toString())
                if(b){
                    gridAdapter.addItems(items)
                }
            }
            recipeMenuKoreanBt.setOnFocusChangeListener { view, b ->
                if(b){
                    var filterList: ArrayList<recipe> = ArrayList()
                    for(recipe in items){
                        if(recipe.category.equals("한식")){
                            filterList.add(recipe)
                        }
                    }
                    gridAdapter.addItems(filterList)
                }
            }
            recipeMenuChineseBt.setOnFocusChangeListener { view, b ->
                if(b){
                    var filterList: ArrayList<recipe> = ArrayList()
                    for(recipe in items){
                        if(recipe.category.equals("중국")){
                            filterList.add(recipe)
                        }
                    }
                    gridAdapter.addItems(filterList)
                }
            }

            recipeMenuJapanBt.setOnFocusChangeListener { view, b ->
                if(b){
                    var filterList: ArrayList<recipe> = ArrayList()
                    for(recipe in items){
                        if(recipe.category.equals("일본")){
                            filterList.add(recipe)
                        }
                    }
                    gridAdapter.addItems(filterList)
                }
            }

            recipeMenuItalianBt.setOnFocusChangeListener { view, b ->
                if(b){
                    var filterList: ArrayList<recipe> = ArrayList()
                    for(recipe in items){
                        if(recipe.category.equals("이탈리아")){
                            filterList.add(recipe)
                        }
                    }
                    gridAdapter.addItems(filterList)
                }
            }

            recipeMenuEtcBt.setOnFocusChangeListener { view, b ->
                if(b){
                    var filterList: ArrayList<recipe> = ArrayList()
                    for(recipe in items){
                        if(recipe.category.equals("퓨전") || recipe.category.equals("동남아시아") ){
                            filterList.add(recipe)
                        }
                    }
                    gridAdapter.addItems(filterList)
                }
            }

        }


        binding.recipeSv.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                gridAdapter.filter.filter(newText)
                return false
            }

        })


        return binding.root
    }
    private fun hideKeyBoard(){
        if(activity != null && requireActivity().currentFocus != null){
            val inputManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            binding.recipeMenuAllBt.requestFocus()
        }
    }
}