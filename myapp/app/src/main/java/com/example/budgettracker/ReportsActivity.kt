package com.example.budgettracker

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.budgettracker.databinding.ActivityReportsBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReportsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up date range
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        
        // Set start date to first day of current month
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        binding.textStartDate.text = dateFormat.format(calendar.time)
        
        // Set end date to current date
        calendar.timeInMillis = System.currentTimeMillis()
        binding.textEndDate.text = dateFormat.format(calendar.time)

        // Load report data
        loadReportData()
    }

    private fun loadReportData() {
        // TODO: Replace with actual database queries
        // Monthly Overview
        binding.textTotalExpenses.text = "$1,500.00"
        binding.textAverageDaily.text = "$50.00"
        binding.textTopCategory.text = "Food ($600.00)"

        // Category Distribution
        // TODO: Update pie chart with actual data

        // Key Statistics
        binding.textMonthlyBudget.text = "$2,000.00"
        binding.textRemainingBudget.text = "$500.00"
        binding.textBudgetProgress.text = "75%"
        binding.progressBudget.progress = 75
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_reports, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_export -> {
                // TODO: Implement export functionality
                Toast.makeText(this, "Export Report", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_filter -> {
                // TODO: Show filter dialog
                Toast.makeText(this, "Filter Report", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
} 