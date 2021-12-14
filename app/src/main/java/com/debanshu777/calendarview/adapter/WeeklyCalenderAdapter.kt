package com.debanshu777.calendarview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.debanshu777.calendarview.R
import java.time.LocalDate

class WeeklyCalenderAdapter (
    private val nameOfYear: ArrayList<LocalDate?>,
    private val onItemListener: OnItemListener,
    private val selectedDate:LocalDate
) :
RecyclerView.Adapter<WeeklyCalenderAdapter.WeeklyCalenderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyCalenderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.monthly_calendar_cell_view, parent, false)
        return WeeklyCalenderViewHolder(view, onItemListener)
    }

    override fun onBindViewHolder(holder: WeeklyCalenderViewHolder, position: Int) {
        val date = nameOfYear[position]
        holder.cellYearText.text = date?.dayOfMonth?.toString() ?: ""
        if (selectedDate == date){
            holder.calenderCell.setBackgroundColor(R.drawable.custom_button)
        }
        if(LocalDate.now().equals(date)){
            holder.calenderCell.setBackgroundResource(R.drawable.custom_button)
        }
    }

    override fun getItemCount(): Int {
        return nameOfYear.size
    }

    interface OnItemListener {
        fun onItemClick(position: Int, dayText: String?)
    }

    inner class WeeklyCalenderViewHolder(itemView: View, private val onItemListener: OnItemListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val cellYearText: TextView = itemView.findViewById(
            R.id.cellDayText
        )
        val calenderCell: ConstraintLayout = itemView.findViewById(
            R.id.calender_cell
        )
        override fun onClick(view: View?) {
            onItemListener.onItemClick(adapterPosition, cellYearText.text as String)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}
