package com.debanshu777.calendarview.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.debanshu777.calendarview.R
import com.debanshu777.calendarview.databinding.ActivityMainBinding
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: CalenderViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_CalenderView)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(this)[CalenderViewModel::class.java]

        val calenderInstance = Calendar.getInstance()
        val year = calenderInstance.get(Calendar.YEAR)
        val month = calenderInstance.get(Calendar.MONTH)
        val day = calenderInstance.get(Calendar.DAY_OF_MONTH)
        binding.fab.setOnClickListener {
            DatePickerDialog(
                this,
                AlertDialog.THEME_HOLO_LIGHT,
                { _, year, monthOfYear, dayOfMonth ->
                    val dateCalculation =
                        if (dayOfMonth >= 10) (dayOfMonth).toString() else ("0$dayOfMonth")
                    val monthCalculation =
                        if (monthOfYear + 1 >= 10) (monthOfYear + 1).toString() else ("0${monthOfYear + 1}")
                    viewModel.setSelectedDate(LocalDate.parse("$year-$monthCalculation-$dateCalculation"))
                },
                year,
                month,
                day
            ).show()
        }
    }

}