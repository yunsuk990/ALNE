package com.example.alne.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alne.GlobalApplication
import com.example.alne.model.Comment
import com.example.alne.model.DeleteFavorite
import com.example.alne.model.FavoriteRespond
import com.example.alne.model.RecipeProcess
import com.example.alne.model.RecipeProcessRespond
import com.example.alne.model.Status
import com.example.alne.model.UserId
import com.example.alne.repository.recipeRepository
import com.example.alne.Network.AuthResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeDetailViewModel: ViewModel() {
    private val repository = recipeRepository()


    //댓글 조회
    private val _usersCommentsLiveData = MutableLiveData<Comment>()
    val usersCommentsLiveData: LiveData<Comment> = _usersCommentsLiveData

    //댓글 작성
    private val _addUserCommentLiveData = MutableLiveData<Boolean>()
    val addUserCommentLiveData: LiveData<Boolean> = _addUserCommentLiveData

    //레시피 순서 조회
    private val _getRecipeProcessLiveData = MutableLiveData<RecipeProcess>()
    val getRecipeProcessLiveData: LiveData<RecipeProcess> = _getRecipeProcessLiveData

    //즐겨찾기 등록
    private val _addRecipeFavoriteLiveData = MutableLiveData<Boolean>()
    val addRecipeFavoriteLiveData: LiveData<Boolean> = _addRecipeFavoriteLiveData

    //즐겨찾기 삭제
    private val _deleteRecipeFavoriteLiveData = MutableLiveData<Boolean>()
    val deleteRecipeFavoriteLiveData: LiveData<Boolean> = _deleteRecipeFavoriteLiveData


    fun addUserComment(comment: Comment) = repository.addUserComment(comment).enqueue(object: Callback<AuthResponse>{
        override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
            var res = response.body()
            when(res?.status){
                200 -> {
                    if(res.data == 1){
                        _addUserCommentLiveData.postValue(true)
                    }else{
                        _addUserCommentLiveData.postValue(false)
                    }
                    Log.d("addUserComment_res", res.toString())
                }
            }
        }

        override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
            Log.d("addUserComment_onFailure", t.message.toString())
            _addUserCommentLiveData.postValue(false)
        }
    })

    fun getRecipeProcess(recipeCode: Int) = repository.getRecipeProcess(recipeCode).enqueue(object: Callback<RecipeProcessRespond>{
        override fun onResponse(
            call: Call<RecipeProcessRespond>,
            response: Response<RecipeProcessRespond>,
        ) {
            val res = response.body()
            when(res?.status){
                200 -> {
                    Log.d("getRecipeProcess_onSuccess", res.toString())
                    _getRecipeProcessLiveData.postValue(res.data)
                }
            }
        }

        override fun onFailure(call: Call<RecipeProcessRespond>, t: Throwable) {
            Log.d("getRecipeProcess_onFailure", t.message.toString())
        }

    })

    fun addRecipeFavorite(recipeCode: Int ,userId: UserId = UserId(GlobalApplication.prefManager.getUserToken()?.userId!!, null)) = repository.addRecipeFavorite(recipeCode, userId).enqueue(object: Callback<FavoriteRespond>{
        override fun onResponse(call: Call<FavoriteRespond>, response: Response<FavoriteRespond>) {
            val res = response.body()
            when(res?.status){
                200 -> {
                    _addRecipeFavoriteLiveData.postValue(true)
                    Log.d("addRecipeFavorite_onSuccess", res.toString())
                }
                else -> {
                    _addRecipeFavoriteLiveData.postValue(false)
                    Log.d("addRecipeFavorite_onSuccess:fail", res.toString())
                }
            }
        }

        override fun onFailure(call: Call<FavoriteRespond>, t: Throwable) {
            Log.d("addRecipeFavorite_onFailure", t.message.toString())
            _addRecipeFavoriteLiveData.postValue(false)
        }

    })


    fun deleteRecipeFavorite(delete: DeleteFavorite) = repository.deleteRecipeFavorite(delete).enqueue(object: Callback<Status>{
        override fun onResponse(call: Call<Status>, response: Response<Status>) {
            val res = response.body()
            when(res?.status){
                200 -> {
                    _deleteRecipeFavoriteLiveData.postValue(true)
                    Log.d("deleteRecipeFavorite_onSuccess", res.toString())
                }
                else -> {
                    _deleteRecipeFavoriteLiveData.postValue(false)
                    Log.d("deleteRecipeFavorite_onSuccess:fail", res.toString())
                }
            }
        }

        override fun onFailure(call: Call<Status>, t: Throwable) {
            _deleteRecipeFavoriteLiveData.postValue(false)
            Log.d("deleteRecipeFavorite_onFailure", t.message.toString())
        }

    })


}