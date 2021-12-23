package com.debanshu777.calendarview.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.debanshu777.calendarview.R
import com.debanshu777.calendarview.ui.adapter.LocaleDateCalendarAdapter
import com.debanshu777.calendarview.databinding.FragmentWeeklyViewBinding
import com.debanshu777.calendarview.utils.CalenderUtils
import com.debanshu777.calendarview.ui.CalenderViewModel
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate

class WeeklyView : Fragment(), LocaleDateCalendarAdapter.OnItemListener {

    private var _binding: FragmentWeeklyViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CalenderViewModel
    private lateinit var selectedDate: LocalDate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeeklyViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[CalenderViewModel::class.java]
        binding.monthlyChangeButton.setOnClickListener {
            findNavController().navigate(R.id.action_WeeklyView_to_MonthlyView)
        }
        viewModel.formatDateString.observe(
            viewLifecycleOwner, { value ->
                binding.weeklyDisplayTextView.text = value
            }
        )
        viewModel.selectedDate.observe(
            viewLifecycleOwner, { value ->
                selectedDate = value
                val calenderAdapter =
                    LocaleDateCalendarAdapter(CalenderUtils.daysInWeekArray(value), this, selectedDate)
                val layoutManager = GridLayoutManager(context, 7)
                binding.weeklyCalenderRecyclerView.layoutManager = layoutManager
                binding.weeklyCalenderRecyclerView.adapter = calenderAdapter
                if(selectedDate.month == LocalDate.now().month && selectedDate.year ==LocalDate.now().year){
                    binding.todayButton.visibility=View.INVISIBLE
                }else{
                    binding.todayButton.visibility=View.VISIBLE
                }
            }
        )
        binding.weeklyDisplayLeftShift.setOnClickListener {
            viewModel.setSelectedDate(selectedDate.minusWeeks(1))
        }
        binding.weeklyDisplayRightShift.setOnClickListener {
            viewModel.setSelectedDate(selectedDate.plusWeeks(1))
        }
        binding.todayButton.setOnClickListener {
            viewModel.setSelectedDate(LocalDate.now())
        }
    }

    override fun onItemClick(position: Int, dayText: String?) {
        val dateCalculation =
            if (dayText!!.toInt() >= 10) (dayText).toInt() else ("0${dayText}".toInt())
        viewModel.selectedDate.value =
            LocalDate.of(selectedDate.year, selectedDate.month, dateCalculation)
        Snackbar.make(
            requireView(),
            "Selected Date: $dayText ${CalenderUtils.monthYearFromDate(selectedDate)}",
            Snackbar.LENGTH_LONG
        ).show()
    }

}