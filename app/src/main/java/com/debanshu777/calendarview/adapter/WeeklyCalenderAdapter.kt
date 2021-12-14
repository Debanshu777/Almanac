package com.debanshu777.calendarview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.debanshu777.calendarview.R

class WeeklyCalenderAdapter (
    private val nameOfYear: ArrayList<String>,
    private val onItemListener: OnItemListener
) :
RecyclerView.Adapter<WeeklyCalenderAdapter.WeeklyCalenderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyCalenderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.monthly_calendar_cell_view, parent, false)
        return WeeklyCalenderViewHolder(view, onItemListener)
    }

    override fun onBindViewHolder(holder: WeeklyCalenderViewHolder, position: Int) {
        holder.cellYearText.text = nameOfYear[position]
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
        override fun onClick(view: View?) {
            onItemListener.onItemClick(adapterPosition, cellYearText.text as String)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}
