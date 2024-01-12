package com.example.alne.view.Fridge

import android.R
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.alne.databinding.ItemFoodDetailBinding
import com.example.alne.databinding.ItemFoodaddBinding
import com.example.alne.model.Food
import com.example.alne.model.Jwt
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Calendar

class CustomDialogDetail(context: Context, val food: Food, myCustomDialogDetailInterface: MyCustomDialogDetailInterface): DialogFragment() {
    private lateinit var binding: ItemFoodDetailBinding
    var storage: String? = null
    val myAdapter = ArrayAdapter(
        context,
        R.layout.simple_spinner_dropdown_item,
        arrayListOf("냉장", "냉동")
    )
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val time = System.currentTimeMillis()
    val timeFormat = SimpleDateFormat("hh:mm")

    private var myCustomDialogDetailInterface:MyCustomDialogDetailInterface? = null

    // 인터페이스 연결
    init {
        this.myCustomDialogDetailInterface = myCustomDialogDetailInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemFoodDetailBinding.inflate(inflater, container, false)
        var hour = timeFormat.format(time).split(":")[0]
        var minute = timeFormat.format(time).split(":")[1]
        var date: String? = null
        var time: String? = null

        binding.foodTitleEt.setText(food.name)
        binding.foodMemoTv.setText(food.memo)

        binding.submitBt.setOnClickListener {
            val title = binding.foodTitleEt.text.toString()
            Log.d("data", title + " "+ storage)
//            myCustomDialogDetailInterface?.onSubmitBtnClicked(Food(jwt.userId,title,date + " " + time,binding
//                .foodMemoTv.text.toString(),storage!!,null))
            dismiss()
        }

        binding.cancelBt.setOnClickListener {
            dismiss()
        }

        val spinner = binding.spinner
        spinner.adapter = myAdapter
        spinner.setSelection(0)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2){
                    0 -> {
                        storage = "COLD"
                    }
                    1-> {
                        storage = "FROZEN"
                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        binding.foodAddDatePicker.text = "${year}.${month}.${dayOfMonth}"
        var mDateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                date = "${p1}.${p2}.${p3}"
                binding.foodAddDatePicker.text = date
            }
        }
        binding.foodAddTimePicker.text = "${hour}:${minute}"
        var mTimeSetListener = object: TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                Log.d("time",p1.toString() + " " + p2.toString())
                time = "${p1}:${p2}"
                binding.foodAddTimePicker.text = time
            }
        }
        binding.foodAddDatepickerLinear.setOnClickListener{
            DatePickerDialog(requireContext(),
                AlertDialog.THEME_HOLO_LIGHT,mDateSetListener, year,month,dayOfMonth).show()
        }
        binding.foodAddTimepickerLinear.setOnClickListener{
            TimePickerDialog(requireContext(), AlertDialog.THEME_HOLO_LIGHT, mTimeSetListener, Integer.parseInt(hour),  Integer.parseInt(minute),true).show()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dialogFragmentResize()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun dialogFragmentResize() {
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
}

interface MyCustomDialogDetailInterface {
    fun onSubmitBtnClicked(food: Food)
}