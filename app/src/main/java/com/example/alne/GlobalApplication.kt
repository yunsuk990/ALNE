package com.example.alne

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication():Application() {

    companion object {
        lateinit var prefManager: SharedPrefManager
    }

    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들
        prefManager = SharedPrefManager(applicationContext)
        // Kakao SDK 초기화
        KakaoSdk.init(this, "ee52fc7baab1b48b61bcf3e7f3b617f0")
    }
}