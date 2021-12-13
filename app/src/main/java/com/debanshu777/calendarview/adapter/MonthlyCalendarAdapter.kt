package com.debanshu777.calendarview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.debanshu777.calendarview.R

class MonthlyCalendarAdapter(
    private val daysOfMonth: ArrayList<String>,
    private val onItemListener: OnItemListener
) :
    RecyclerView.Adapter<MonthlyCalendarAdapter.MonthlyCalendarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthlyCalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.monthly_calendar_cell_view, parent, false)
        return MonthlyCalendarViewHolder(view, onItemListener)
    }

    override fun onBindViewHolder(holder: MonthlyCalendarViewHolder, position: Int) {
        holder.cellDayText.text = daysOfMonth[position]
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
        override fun onClick(view: View?) {
            onItemListener.onItemClick(adapterPosition, cellDayText.text as String)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}
