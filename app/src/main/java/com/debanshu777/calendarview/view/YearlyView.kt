package com.debanshu777.calendarview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.debanshu777.calendarview.R
import com.debanshu777.calendarview.adapter.YearlyCalenderAdapter
import com.debanshu777.calendarview.databinding.FragmentYearlyViewBinding
import com.debanshu777.calendarview.utils.CalenderUtils
import com.debanshu777.calendarview.viewModel.CalenderViewModel
import java.time.LocalDate

class YearlyView : Fragment(), YearlyCalenderAdapter.OnItemListener {

    private var _binding: FragmentYearlyViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CalenderViewModel
    private var pageNumber: Int? = 0
    private val currentDate: LocalDate = LocalDate.now()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYearlyViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[CalenderViewModel::class.java]
        viewModel.yearViewPageNumber.observe(viewLifecycleOwner, { pageNo ->
            pageNumber = pageNo
        })
        viewModel.yearArrayList.value = CalenderUtils.yearArray(currentDate, pageNumber!!)

        binding.weeklyChangeButton.setOnClickListener {
            findNavController().navigate(R.id.action_YearlyView_to_WeeklyView)
        }
        binding.yearlyDisplayLeftShift.setOnClickListener {
            viewModel.yearViewPageNumber.value = viewModel.yearViewPageNumber.value?.minus(1)
            viewModel.yearArrayList.value = CalenderUtils.yearArray(currentDate, pageNumber!!)
        }
        binding.yearlyDisplayRightShift.setOnClickListener {
            viewModel.yearViewPageNumber.value = viewModel.yearViewPageNumber.value?.plus(1)
            viewModel.yearArrayList.value = CalenderUtils.yearArray(currentDate, pageNumber!!)
        }
        binding.todayButton.setOnClickListener {
            viewModel.setSelectedDate(LocalDate.now())
            findNavController().navigate(R.id.action_YearlyView_to_MonthlyView)
        }
        viewModel.yearArrayList.observe(viewLifecycleOwner, { yearArrayList ->
            val calenderAdapter = YearlyCalenderAdapter(yearArrayList, this)
            val layoutManager = GridLayoutManager(context, 4)
            binding.yearlyCalenderRecyclerView.layoutManager = layoutManager
            binding.yearlyCalenderRecyclerView.adapter = calenderAdapter
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int, yearText: String?) {
        viewModel.setSelectedDateFromSelectedYear(yearText!!.toInt())
        findNavController().navigate(R.id.action_YearlyView_to_MonthlyView)
    }
}