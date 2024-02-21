package com.example.alne.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alne.model.Food
import com.example.alne.model.Recipe
import com.example.alne.repository.recipeRepository
import com.example.alne.repository.repository
import com.example.alne.room.model.recipe
import com.example.flo.Network.RecipeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeViewModel() : ViewModel() {

    private val repository = recipeRepository()
    private val _getRecipeLiveData = MutableLiveData<ArrayList<recipe>>()
    val getRecipeLiveData: LiveData<ArrayList<recipe>> = _getRecipeLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _getRecipeLiveData.postValue(repository.getAllRecipe())
        }
    }
}