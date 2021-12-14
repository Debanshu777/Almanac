package com.debanshu777.calendarview.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.debanshu777.calendarview.R
import com.debanshu777.calendarview.databinding.FragmentMonthlyViewBinding
import com.debanshu777.calendarview.databinding.FragmentWeeklyViewBinding
import com.debanshu777.calendarview.databinding.FragmentYearlyViewBinding

class WeeklyView : Fragment() {

    private var _binding: FragmentWeeklyViewBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeeklyViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.monthlyChangeButton.setOnClickListener {
            findNavController().navigate(R.id.action_WeeklyView_to_MonthlyView)
        }
    }

}