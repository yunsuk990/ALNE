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
import androidx.fragment.app.DialogFragment
import com.example.alne.databinding.ItemFoodDetailBinding
import com.example.alne.model.Food
import com.example.alne.model.Jwt
import java.util.Calendar

class CustomDialogDetail(context: Context, val jwt: Jwt, val food: Food, myCustomDialogDetailInterface: MyCustomDialogDetailInterface): DialogFragment() {
    private lateinit var binding: ItemFoodDetailBinding
    var storage: String? = null
    val myAdapter = ArrayAdapter(
        context,
        R.layout.simple_spinner_dropdown_item,
        arrayListOf("냉장", "냉동")
    )

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
        var dateString: String = food.exp!!
        var date = dateString.split(" ")[0]
        var time = dateString.split(" ")[1]
        var calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, date.split(".")[0].toInt())
        calendar.set(Calendar.MONTH, date.split(".")[1].toInt())
        calendar.set(Calendar.DAY_OF_MONTH, date.split(".")[2].toInt())
        calendar.set(Calendar.HOUR, time.split(":")[0].toInt())
        calendar.set(Calendar.MINUTE, time.split(":")[1].toInt())

        binding.foodTitleEt.setText(food.name)
        binding.foodMemoTv.setText(food.memo)

        binding.submitBt.setOnClickListener {
            val title = binding.foodTitleEt.text.toString()
            Log.d("data", title + " "+ storage)
            myCustomDialogDetailInterface?.onSubmitBtnDetailClicked(Food(jwt.userId,title,date + " " + time,binding
                .foodMemoTv.text.toString(),storage!!,null))
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
        binding.foodAddDatePicker.text = "${calendar.get(Calendar.YEAR)}.${String.format("%02d",calendar.get(Calendar.MONTH))}.${String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH))}"
        var mDateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                date = "${p1}.${p2+1}.${p3}"
                binding.foodAddDatePicker.text = date
            }
        }
        binding.foodAddTimePicker.text = "${String.format("%02d",calendar.get(Calendar.HOUR))}:${String.format("%02d",calendar.get(Calendar.MINUTE))}"
        var mTimeSetListener = object: TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                Log.d("time",p1.toString() + " " + p2.toString())
                time = "${p1}:${p2}"
                binding.foodAddTimePicker.text = time
            }
        }
        binding.foodAddDatepickerLinear.setOnClickListener{
            DatePickerDialog(requireContext(),
                AlertDialog.THEME_HOLO_LIGHT,mDateSetListener, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.foodAddTimepickerLinear.setOnClickListener{
            TimePickerDialog(requireContext(), AlertDialog.THEME_HOLO_LIGHT, mTimeSetListener, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),true).show()
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


// 재료 수정하기(편집)
interface MyCustomDialogDetailInterface {
    fun onSubmitBtnDetailClicked(food: Food)
}