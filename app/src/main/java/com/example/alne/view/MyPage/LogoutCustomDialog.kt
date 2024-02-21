package com.example.alne.view.MyPage

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.alne.GlobalApplication
import com.example.alne.databinding.LogoutDialogBinding
import com.example.alne.view.Splash.StartActivity
import com.kakao.sdk.user.UserApiClient

class LogoutCustomDialog: DialogFragment() {

    lateinit var binding: LogoutDialogBinding

    companion object {
        const val TAG = "LogoutCustomDialog"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LogoutDialogBinding.inflate(layoutInflater)

        binding.dialogQuitBt.setOnClickListener {
            dismiss()
        }

        binding.dialogSubmitBt.setOnClickListener {
            logOut()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager?.defaultDisplay
        val size = Point()
        display?.getSize(size)
        val deviceWidth = size.x
        val deviceHeght = size.y
        val params = dialog?.window?.attributes
        params?.width = (deviceWidth*0.95).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun logOut(){
        binding.dialogSubmitBt.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Log.e("logout", "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                    GlobalApplication.prefManager.deleteAutoLogin()
                    startActivity(Intent(requireContext(), StartActivity::class.java))
                }
                else {
                    startActivity(Intent(requireContext(), StartActivity::class.java))
                    Log.i("logout", "로그아웃 성공. SDK에서 토큰 삭제됨")
                }
            }
        }
    }
}