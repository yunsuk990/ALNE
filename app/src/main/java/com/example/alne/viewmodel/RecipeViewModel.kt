package com.example.alne.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alne.model.Food
import com.example.alne.model.Recipe
import com.example.alne.repository.repository
import com.example.flo.Network.RecipeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeViewModel: ViewModel() {

    private val repository = repository()
    private val _getRecipeLiveData = MutableLiveData<ArrayList<Recipe>>()
    val getRecipeLiveData: LiveData<ArrayList<Recipe>> = _getRecipeLiveData

    fun getAllRecipe(){
        repository.getAllRecipe().enqueue(object: Callback<RecipeResponse> {
            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>,
            ) {
                var res = response.body()
                when(res?.status){
                    200 ->{
                        _getRecipeLiveData.postValue(res.data)
                        for(recipe in res.data){

                        }
                        Log.d("getAllRecipe", res.data.toString())
                    }
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {

            }

        })
    }
}