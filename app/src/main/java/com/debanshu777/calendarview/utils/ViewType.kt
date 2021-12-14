package com.debanshu777.calendarview.utils

sealed class ViewType(val type: String) {
    object YearView : ViewType("YEAR")
    object MonthView : ViewType("MONTH")
}