package com.debanshu777.calendarview.utils

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

object CalenderUtils {
    val monthYearFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")

    fun monthYearFromDate(date: LocalDate): String = date.format(monthYearFormatter)

    fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthList: ArrayList<String> = ArrayList()
        val yearMonth: YearMonth = YearMonth.from(date)

        val daysInMonth: Int = yearMonth.lengthOfMonth()
        val firstDayOfMonth: LocalDate = date.withDayOfMonth(1)
        val dayOfWeek = firstDayOfMonth.dayOfWeek.value
        for ( i in 1..42 ){
            if(i<=dayOfWeek || i> (daysInMonth+dayOfWeek)){
                daysInMonthList.add("")
            }else{
                daysInMonthList.add((i-dayOfWeek).toString())
            }
        }
        return daysInMonthList
    }
}