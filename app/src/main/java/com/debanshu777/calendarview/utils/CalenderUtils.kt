package com.debanshu777.calendarview.utils

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

object CalenderUtils {
    private const val LIMIT_PER_PAGE: Int = 12

    val dayMonthYearFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
    val monthYearFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")

    fun dayMonthYearFromDate(date: LocalDate): String = date.format(dayMonthYearFormatter)

    fun monthYearFromDate(date: LocalDate): String = date.format(monthYearFormatter)

    fun daysInWeekArray(date: LocalDate?): ArrayList<LocalDate?> {
        val daysInWeekList: ArrayList<LocalDate?> = ArrayList()
        var current: LocalDate? = sundayForDate(date)
        val endDate = current!!.plusWeeks(1)
        while (current!!.isBefore(endDate)) {
            daysInWeekList.add(current)
            current = current.plusDays(1)
        }
        return daysInWeekList
    }

    private fun sundayForDate(date: LocalDate?): LocalDate? {
        var current = date
        val oneWeekAgo: LocalDate = current!!.minusWeeks(1)
        while (current!!.isAfter(oneWeekAgo)) {
            if (current.dayOfWeek == DayOfWeek.SUNDAY)
                return current
            current = current.minusDays(1)
        }
        return null
    }

    fun daysInMonthArray(date: LocalDate): ArrayList<LocalDate?> {
        val daysInMonthList: ArrayList<LocalDate?> = ArrayList()
        val yearMonth: YearMonth = YearMonth.from(date)

        val daysInMonth: Int = yearMonth.lengthOfMonth()
        val firstDayOfMonth: LocalDate = date.withDayOfMonth(1)
        val dayOfWeek = firstDayOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > (daysInMonth + dayOfWeek)) {
                daysInMonthList.add(null)
            } else {
                daysInMonthList.add(LocalDate.of(date.year, date.month, i - dayOfWeek))
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