package com.debanshu777.calendarview.utils

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

object CalenderUtils {
    val LIMIT_PER_PAGE: Int = 12

    val monthYearFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")

    fun monthYearFromDate(date: LocalDate): String = date.format(monthYearFormatter)

    fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthList: ArrayList<String> = ArrayList()
        val yearMonth: YearMonth = YearMonth.from(date)

        val daysInMonth: Int = yearMonth.lengthOfMonth()
        val firstDayOfMonth: LocalDate = date.withDayOfMonth(1)
        val dayOfWeek = firstDayOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > (daysInMonth + dayOfWeek)) {
                daysInMonthList.add("")
            } else {
                daysInMonthList.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthList
    }

    fun yearArray(date: LocalDate, page: Int): ArrayList<String> {
        val yearArray: ArrayList<String> = ArrayList()
        val currentYear: Int = date.year
        var startingYear = currentYear - LIMIT_PER_PAGE
        var endingYear = currentYear - 1
        if (page != -1) {
            startingYear =
                if (page >= 0) currentYear + (LIMIT_PER_PAGE * page) else (currentYear + page * LIMIT_PER_PAGE)
            endingYear =
                if (page >= 0) currentYear + (LIMIT_PER_PAGE * (page + 1)) - 1 else (currentYear + (page * LIMIT_PER_PAGE)) + LIMIT_PER_PAGE - 1
        }
        for (i in startingYear..endingYear) {
            yearArray.add(i.toString())
        }
        return yearArray
    }
}