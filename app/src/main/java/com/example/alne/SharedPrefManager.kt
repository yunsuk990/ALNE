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

    private val LOGIN_SETTING = "login_setting"


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

    fun saveJwt(data: Jwt){
        Log.d("jwt" , data.toString())
        val sharedPreferences = context?.getSharedPreferences(USER_INFO, AppCompatActivity.MODE_PRIVATE)!!
        val edit = sharedPreferences.edit()
        edit.putString(KEY_USER_INFO, Gson().toJson(data))
        edit.commit()
    }
    fun getUserToken(): Jwt {
        val sharedPreferences = context?.getSharedPreferences(USER_INFO, AppCompatActivity.MODE_PRIVATE)
        val userJwt = Gson().fromJson(sharedPreferences?.getString(KEY_USER_INFO,null), Jwt::class.java)
        Log.d("getjwt", userJwt.toString())
        return userJwt
    }

    // 자동로그인, 아이디기록 여부 저장
    fun saveLoginSetting(saveId: Boolean, autoLogin: Boolean){
        val sharedPreferences = context?.getSharedPreferences(LOGIN_SETTING, AppCompatActivity.MODE_PRIVATE)
        val edit = sharedPreferences?.edit()!!
        edit.putBoolean("saveId", saveId)
        edit.putBoolean("autoLogin", autoLogin)
        edit.commit()
    }

    fun getLoginSetting(): ArrayList<Boolean> {
        val sharedPreferences = context?.getSharedPreferences(LOGIN_SETTING, AppCompatActivity.MODE_PRIVATE)!!
        return arrayListOf(sharedPreferences.getBoolean("saveId", false), sharedPreferences.getBoolean("autoLogin", false))
    }

    // 정보 삭제
    fun deleteAutoLogin(){
        val sharedPreferences1 = context?.getSharedPreferences(LOGIN_SETTING, AppCompatActivity.MODE_PRIVATE)!!
        val sharedPreferences = context?.getSharedPreferences(USER_INFO, AppCompatActivity.MODE_PRIVATE)!!
        val edit = sharedPreferences.edit()
        var edit1 = sharedPreferences1.edit()
        edit.clear()
        edit1.commit()
        edit1.commit()
        edit.commit()
    }


}