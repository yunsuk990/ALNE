package com.example.alne.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alne.Network.FridgeGetResponse
import com.example.alne.Network.FridgePostResponse
import com.example.alne.model.Food
import com.example.alne.model.UserId
import com.example.alne.repository.repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FridgeViewModel: ViewModel() {

    private val repository = repository()

    //재료 등록 정보
    private val _getFridgeLiveData = MutableLiveData<ArrayList<Food>>()
    val getFridgeLiveData: LiveData<ArrayList<Food>> = _getFridgeLiveData

    //재료 등록 성공 여부
    private val _addFridgeLiveData = MutableLiveData<Boolean>()
    val addFridgeLiveData: LiveData<Boolean> = _addFridgeLiveData

    fun addFridgeData(accessToken: String,food: Food){
        repository.addFridgeData(accessToken,food).enqueue(object: Callback<FridgePostResponse>{
            override fun onResponse(
                call: Call<FridgePostResponse>,
                response: Response<FridgePostResponse>,
            ) {
                Log.d("addFridgeData", "onSuccess")
                Log.d("addFridgeData", response.body().toString())
                if(response.isSuccessful) {
                    var res = response.body()
                    when(res?.status){
                        200 -> {
                            Log.d("addFridgeData", "재료 등록 성공")
                            Log.d("addFridgeData", response.body()?.data.toString())
                            _addFridgeLiveData.postValue(true)
                        }
                        401 -> {
                            _addFridgeLiveData.postValue(true)
                            Log.d("addFridgeData", "재료 등록 실패")
                        }
                    }
                }
            }
            override fun onFailure(call: Call<FridgePostResponse>, t: Throwable) {
                Log.d("addFridgeData", "onFailure")
            }
        })
    }

    fun getFridgeFood(accessToken: String, userId: UserId){
        Log.d("accessToken", accessToken)
        Log.d("userId", userId.toString())
        repository.getFridgeFood(accessToken, userId).enqueue(object: Callback<FridgeGetResponse>{
            override fun onResponse(
                call: Call<FridgeGetResponse>,
                response: Response<FridgeGetResponse>,
            ) {
                Log.d("getFridgeFood", "onSuccess")
                if(response.isSuccessful) {
                    var res = response.body()
                    when(res?.status){
                        200 -> {
                            var fridge = res.data
                            var items: ArrayList<Food> = ArrayList()
                            for(item in fridge){
                                var name = item.ingredient.name
                                var image = item.ingredient.image
                                var storage = item.storage
                                var expire = item.exp
                                var memo = item.memo
                                var food = Food(item.ingredient.id, name, expire, memo, storage, image)
                                items.add(food)
                            }
                            _getFridgeLiveData.postValue(items)
                        }
                    }
                }else{
                    Log.d("getFridgeFood", response.body().toString())
                }
            }

            override fun onFailure(call: Call<FridgeGetResponse>, t: Throwable) {
                Log.d("getFridgeFood", t.message.toString())
            }

        })
    }
}