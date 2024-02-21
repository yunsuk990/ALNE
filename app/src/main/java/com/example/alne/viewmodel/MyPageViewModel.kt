package com.example.alne.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alne.GlobalApplication

class MyPageViewModel: ViewModel() {


    private val _authStateRespond = MutableLiveData<Boolean>()
    val authStateRespond: LiveData<Boolean> = _authStateRespond

    init {
        if(GlobalApplication.prefManager.getUserToken() != null){
            _authStateRespond.postValue(true)
        }else{
            _authStateRespond.postValue(false)
        }
    }


}