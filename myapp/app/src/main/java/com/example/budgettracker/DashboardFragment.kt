package com.example.budgettracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.budgettracker.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up click listeners for quick actions
        binding.cardAddExpense.setOnClickListener {
            // Navigate to AddExpenseActivity
        }

        binding.cardViewExpenses.setOnClickListener {
            // Navigate to ExpenseListActivity
        }

        binding.cardViewReports.setOnClickListener {
            // Navigate to ReportsActivity
        }

        // Update summary information
        updateSummaryInfo()
    }

    private fun updateSummaryInfo() {
        // TODO: Replace with actual data from database
        binding.textTotalExpenses.text = "$0.00"
        binding.textMonthlyBudget.text = "$0.00"
        binding.textRemainingBudget.text = "$0.00"
        binding.textTopCategory.text = "No expenses yet"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 