package com.example.alne

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.alne.model.Jwt
import com.google.gson.Gson

class SharedPrefManager(val context: Context) {

    private val SHARED_SHEARCH_HISTORY = "shared_search_history"
    private val KEY_SEARCH_HISTORY = "key_search_history"

    private val USER_INFO = "user_info"
    private val KEY_USER_INFO = "jwt"


//    // 검색 목록을 저장
//    fun storeSearchHistoryList(searchHistoryList: MutableList<SearchData>){
//        Log.d("storeSearchHistoryList", searchHistoryList.toString())
//        // 매개변수로 들어온 배열을 -> 문자열로 변환
//        val searchHistoryListString : String = Gson().toJson(searchHistoryList)
//        val shared = context.getSharedPreferences(SHARED_SHEARCH_HISTORY, Context.MODE_PRIVATE)
//        val editor = shared.edit()
//        editor.putString(KEY_SEARCH_HISTORY, searchHistoryListString)
//        editor.apply()
//
//    }
//
//    //검색 목록 가져오기
//    fun getSearchHistoryList(): MutableList<SearchData>{
//        val shared = context.getSharedPreferences(SHARED_SHEARCH_HISTORY, Context.MODE_PRIVATE)
//        val storedSearchHistoryListString = shared.getString(KEY_SEARCH_HISTORY, "")!!
//        Log.d("getSearchHistoryList", storedSearchHistoryListString.toString())
//        var storedSearchHistoryList = ArrayList<SearchData>()
//        if(storedSearchHistoryListString.isNotEmpty()){
//            storedSearchHistoryList = Gson().
//                    fromJson(storedSearchHistoryListString, Array<SearchData>::class.java).
//                    toMutableList() as ArrayList<SearchData>
//        }
//        return storedSearchHistoryList
//    }
//
//    // 검색 목록 지우기
//    fun clearSearchHistoryList(){
//        val shared = context.getSharedPreferences(SHARED_SHEARCH_HISTORY, Context.MODE_PRIVATE)
//        val editor = shared.edit()
//        editor.clear()
//        editor.apply()
//    }

    fun getUserToken(): Jwt {
        val sharedPreferences = context?.getSharedPreferences(USER_INFO, AppCompatActivity.MODE_PRIVATE)
        val userJwt = Gson().fromJson(sharedPreferences?.getString(KEY_USER_INFO,null), Jwt::class.java)
        Log.d("getjwt", userJwt.toString())
        return userJwt
    }


}