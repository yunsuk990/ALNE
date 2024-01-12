package com.example.alne

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication():Application() {
    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoSdk.init(this, "ee52fc7baab1b48b61bcf3e7f3b617f0")
    }
}