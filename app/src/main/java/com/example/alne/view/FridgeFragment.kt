package com.example.alne.view

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TimeUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< Updated upstream
=======
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
>>>>>>> Stashed changes
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.Spinner
<<<<<<< Updated upstream
import androidx.appcompat.widget.AppCompatButton
=======
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.DialogFragment
import com.example.alne.Network.FridgeApi
import com.example.alne.Network.FridgePostResponse
>>>>>>> Stashed changes
import com.example.alne.databinding.FragmentFridgeBinding
import com.example.alne.model.Food
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText
<<<<<<< Updated upstream
=======
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import com.example.alne.R
import com.example.alne.databinding.ItemFoodaddBinding
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone
>>>>>>> Stashed changes

class FridgeFragment : Fragment() {

    lateinit var binding: FragmentFridgeBinding
    private val information = arrayListOf("All", "냉장","냉동")
<<<<<<< Updated upstream
    val items = arrayListOf<String>("냉장", "냉동")
=======
    lateinit var retrofit: Retrofit

>>>>>>> Stashed changes
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.d("FridgeFragment", "onCreateView()")
        // Inflate the layout for this fragment
        binding = FragmentFridgeBinding.inflate(inflater, container, false)

        val fridgeAdapter = FridgeVPAdapter(this)
        binding.fridgeVp.adapter = fridgeAdapter
        TabLayoutMediator(binding.fridgeTl, binding.fridgeVp){ tab, position ->
            tab.text = information[position]
        }.attach()


        binding.fridgeFloatingBt.setOnClickListener{
            CustomDialog(requireContext()).show(requireActivity().supportFragmentManager, "CustomDialog")
        }



        return binding.root
    }

<<<<<<< Updated upstream
    private fun getCustomDialog(food: Food?){
        val myAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item, items)
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(com.example.alne.R.layout.item_foodadd,  null)
        val spinner = view.rootView.findViewById<Spinner>(com.example.alne.R.id.spinner)
        spinner.adapter = myAdapter
        spinner.setSelection(0)
        if(food != null){
            view.rootView.findViewById<TextInputEditText>(com.example.alne.R.id.food_title_et).setText(food.name)
            view.rootView.findViewById<TextInputEditText>(com.example.alne.R.id.food_memo_tv).setText(food.memo)
=======

     class CustomDialog(context: Context): DialogFragment(){
         private lateinit var binding: ItemFoodaddBinding
         var storage: String? = null
         val myAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, arrayListOf("냉장", "냉동"))
         val calendar = Calendar.getInstance()
         val year = calendar.get(Calendar.YEAR)
         val month = calendar.get(Calendar.MONTH)
         val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
         val time =  System.currentTimeMillis()
         val timeFormat = SimpleDateFormat("hh:mm")
>>>>>>> Stashed changes


         override fun onCreateView(
             inflater: LayoutInflater,
             container: ViewGroup?,
             savedInstanceState: Bundle?
         ): View? {
             binding = ItemFoodaddBinding.inflate(inflater, container, false)
             var hour = timeFormat.format(time).split(":")[0]
             var minute = timeFormat.format(time).split(":")[1]

             binding.submitBt.setOnClickListener {
//            fridgeadapter.addFood(Food("사과",0,null,null,"2023-12-20까지"))
<<<<<<< Updated upstream
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

=======
                 val title = binding.foodTitleEt.text.toString()
//            val memo = view.rootView.findViewById<TextInputEditText>(R.id.food_memo_tv).text.toString()
//            val exp = "000000"
                 Log.d("data", title + " "+ storage)
                 FridgeFragment().addFridgeData(title,null,null,storage!!)
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
                     binding.foodAddDatePicker.text = "${p1}.${p2}.${p3}"
                 }
             }

             binding.foodAddTimePicker.text = "${hour}:${minute}"
             var mTimeSetListener = object: TimePickerDialog.OnTimeSetListener{
                 override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                     Log.d("time",p1.toString() + " " + p2.toString())
                     binding.foodAddTimePicker.text = "${p1}:${p2}"
                 }

             }

             binding.foodAddDatepickerLinear.setOnClickListener{
                  DatePickerDialog(requireContext(),AlertDialog.THEME_HOLO_LIGHT,mDateSetListener, year,month,dayOfMonth).show()
              }

             binding.foodAddTimepickerLinear.setOnClickListener{
                 TimePickerDialog(requireContext(), AlertDialog.THEME_HOLO_LIGHT, mTimeSetListener, Integer.parseInt(hour),  Integer.parseInt(minute),true).show()
             }

             return binding.root
         }

         override fun onResume() {
             super.onResume()
             dialogFragmentResize(requireContext(), this, 1f, 0.7f)
         }

         fun dialogFragmentResize(context: Context, dialogFragment: DialogFragment, width: Float, height: Float) {

             val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

             if (Build.VERSION.SDK_INT < 30) {

                 val display = windowManager.defaultDisplay
                 val size = Point()

                 display.getSize(size)

                 val window = dialogFragment.dialog?.window

                 val x = (size.x * width).toInt()
                 val y = (size.y * height).toInt()
                 window?.setLayout(x, y)
             } else {

                 val rect = windowManager.currentWindowMetrics.bounds

                 val window = dialogFragment.dialog?.window

                 val x = (rect.width() * width).toInt()
                 val y = (rect.height() * height).toInt()
                 window?.setLayout(x, y)
             }
         }

    }



    fun addFridgeData(title: String, exp: String?, memo: String?, storage: String){
        retrofit = getRetrofit()
        retrofit.create(FridgeApi::class.java).addFridgeFood(Food(getUserToken(), title,exp,memo,storage, null)).enqueue(object: Callback<FridgePostResponse>{
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
                        }
                        401 -> {
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



>>>>>>> Stashed changes

    override fun onStart() {
        Log.d("FridgeFragment", "onStart()")
        super.onStart()
    }
    override fun onPause() {
        Log.d("FridgeFragment", "onPause()")
        super.onPause()
    }

    override fun onDestroyView() {
        Log.d("FridgeFragment", "onDestroyView()")
        super.onDestroyView()
    }

//    override fun onItemClick(food: Food) {
//        getCustomDialog(food)
//    }
}