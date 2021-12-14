package com.debanshu777.calendarview.view

import android.os.Bundle
import android.transition.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.debanshu777.calendarview.R
import com.debanshu777.calendarview.adapter.MonthlyCalendarAdapter
import com.debanshu777.calendarview.databinding.FragmentMonthlyViewBinding
import com.debanshu777.calendarview.utils.CalenderUtils
import com.debanshu777.calendarview.utils.CalenderUtils.daysInMonthArray
import com.debanshu777.calendarview.viewModel.CalenderViewModel
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate

class MonthlyView : Fragment(), MonthlyCalendarAdapter.OnItemListener {

    private var _binding: FragmentMonthlyViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CalenderViewModel
    private lateinit var selectedDate: LocalDate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonthlyViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[CalenderViewModel::class.java]
        binding.yearChangeButton.setOnClickListener {
            findNavController().navigate(R.id.action_MonthlyView_to_YearlyView)
        }
        viewModel.selectedDate.observe(
            viewLifecycleOwner, { value ->
                selectedDate = value
                val calenderAdapter =
                    MonthlyCalendarAdapter(daysInMonthArray(value), this, selectedDate)
                val layoutManager = GridLayoutManager(context, 7)
                binding.monthlyCalenderRecyclerView.layoutManager = layoutManager
                binding.monthlyCalenderRecyclerView.adapter = calenderAdapter
                if(selectedDate.month == LocalDate.now().month){
                    binding.todayButton.visibility=View.INVISIBLE
                }else{
                    binding.todayButton.visibility=View.VISIBLE
                }
            }
        )
        viewModel.formatDateString.observe(
            viewLifecycleOwner, { value ->
                binding.monthDisplayTextView.text = value
            }
        )

        binding.monthDisplayLeftShift.setOnClickListener {
            viewModel.setSelectedDate(selectedDate.minusMonths(1))
        }
        binding.monthDisplayRightShift.setOnClickListener {
            viewModel.setSelectedDate(selectedDate.plusMonths(1))
        }
        binding.todayButton.setOnClickListener {
            viewModel.setSelectedDate(LocalDate.now())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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