package com.debanshu777.calendarview.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.debanshu777.calendarview.R
import java.time.LocalDate

class MonthlyCalendarAdapter(
    private val daysOfMonth: ArrayList<LocalDate?>,
    private val onItemListener: OnItemListener,
    private val selectedDate:LocalDate
) :
    RecyclerView.Adapter<MonthlyCalendarAdapter.MonthlyCalendarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthlyCalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.monthly_calendar_cell_view, parent, false)
        return MonthlyCalendarViewHolder(view, onItemListener)
    }

    override fun onBindViewHolder(holder: MonthlyCalendarViewHolder, position: Int) {
        val date=daysOfMonth[position]
        holder.cellDayText.text = date?.dayOfMonth?.toString() ?: ""
        if (selectedDate == date){
            holder.calenderCell.setBackgroundColor(R.drawable.custom_button)
        }
        if(LocalDate.now().equals(date)){
            holder.calenderCell.setBackgroundResource(R.drawable.custom_button)
        }
    }

    override fun getItemCount(): Int {
        return daysOfMonth.size
    }

    interface OnItemListener {
        fun onItemClick(position: Int, dayText: String?)
    }

    inner class MonthlyCalendarViewHolder(itemView: View, private val onItemListener: OnItemListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val cellDayText: TextView = itemView.findViewById(
            R.id.cellDayText
        )
        val calenderCell:ConstraintLayout = itemView.findViewById(
            R.id.calender_cell
        )
        override fun onClick(view: View?) {
            onItemListener.onItemClick(adapterPosition, cellDayText.text as String)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}
