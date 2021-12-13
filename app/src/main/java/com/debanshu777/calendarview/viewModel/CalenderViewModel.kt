package com.debanshu777.calendarview.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debanshu777.calendarview.utils.ViewType
import com.debanshu777.calendarview.utils.CalenderUtils
import kotlinx.coroutines.launch
import java.time.LocalDate

class CalenderViewModel : ViewModel() {
    private val viewType = MutableLiveData<ViewType?>(ViewType.MonthView)
    val selectedDate: MutableLiveData<LocalDate> = MutableLiveData(LocalDate.now())
    val selectedYear: MutableLiveData<Int> = MutableLiveData(selectedDate.value!!.year)
    val yearArrayList:MutableLiveData<ArrayList<String>> = MutableLiveData()
    val yearViewPageNumber:MutableLiveData<Int> = MutableLiveData(0)
    val formatDateString: MutableLiveData<String> =
        MutableLiveData(selectedDate.value!!.format(CalenderUtils.monthYearFormatter))

    fun setSelectedDate(date: LocalDate) {
        viewModelScope.launch {
            selectedDate.value = date
            formatDateString.value = selectedDate.value!!.format(CalenderUtils.monthYearFormatter)
        }
    }

    fun setSelectedDateFromSelectedYear(year: Int) {
        viewModelScope.launch {
            selectedDate.value =
                LocalDate.parse("$year-${selectedDate.value!!.month}-${selectedDate.value!!.dayOfMonth}")
            formatDateString.value = selectedDate.value!!.format(CalenderUtils.monthYearFormatter)
        }
    }

}