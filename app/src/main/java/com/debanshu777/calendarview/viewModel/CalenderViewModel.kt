package com.debanshu777.calendarview.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debanshu777.calendarview.model.ViewType
import com.debanshu777.calendarview.utils.CalenderUtils
import kotlinx.coroutines.launch
import java.time.LocalDate

class CalenderViewModel:ViewModel() {
    private val viewType = MutableLiveData<ViewType?>(ViewType.MonthView)
    val selectedDate:MutableLiveData<LocalDate> = MutableLiveData(LocalDate.now())
    val formatDateString:MutableLiveData<String> = MutableLiveData(selectedDate.value!!.format(CalenderUtils.monthYearFormatter))

    fun setSelectedDate(date:LocalDate){
        viewModelScope.launch {
            selectedDate.value=date
            formatDateString.value = selectedDate.value!!.format(CalenderUtils.monthYearFormatter)
        }
    }

}