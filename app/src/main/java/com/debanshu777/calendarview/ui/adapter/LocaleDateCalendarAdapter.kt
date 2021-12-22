package com.debanshu777.calendarview.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.debanshu777.calendarview.R
import java.time.LocalDate

class LocaleDateCalendarAdapter(
    private val daysOfMonth: ArrayList<LocalDate?>,
    private val onItemListener: OnItemListener,
    private val selectedDate: LocalDate
) :
    RecyclerView.Adapter<LocaleDateCalendarAdapter.LocaleDateCalendarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocaleDateCalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.monthly_calendar_cell_view, parent, false)
        return LocaleDateCalendarViewHolder(view, onItemListener)
    }

    override fun onBindViewHolder(holder: LocaleDateCalendarViewHolder, position: Int) {
        val date = daysOfMonth[position]
        holder.cellDayText.text = date?.dayOfMonth?.toString() ?: ""
        if (selectedDate == date) {
            holder.calenderCell.setBackgroundColor(R.drawable.custom_button)
        }
        if (LocalDate.now().equals(date)) {
            holder.calenderCell.setBackgroundResource(R.drawable.custom_button)
        }
    }

    override fun getItemCount(): Int {
        return daysOfMonth.size
    }

    interface OnItemListener {
        fun onItemClick(position: Int, dayText: String?)
    }

    inner class LocaleDateCalendarViewHolder(
        itemView: View,
        private val onItemListener: OnItemListener
    ) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val cellDayText: TextView = itemView.findViewById(
            R.id.cellDayText
        )
        val calenderCell: ConstraintLayout = itemView.findViewById(
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
