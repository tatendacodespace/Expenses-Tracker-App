package com.example.budgettracker

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgettracker.databinding.ActivityExpenseListBinding
import com.example.budgettracker.model.Expense
import java.util.Date

class ExpenseListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExpenseListBinding
    private lateinit var expenseAdapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpenseListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up RecyclerView
        expenseAdapter = ExpenseAdapter(emptyList()) { expense ->
            // TODO: Handle expense item click
            Toast.makeText(this, "Clicked: ${expense.description}", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerViewExpenses.apply {
            layoutManager = LinearLayoutManager(this@ExpenseListActivity)
            adapter = expenseAdapter
        }

        // Load expenses
        loadExpenses()
    }

    private fun loadExpenses() {
        // TODO: Replace with actual database query
        val dummyExpenses = listOf(
            Expense(1, 50.0, "Lunch", "Food", Date(), 1),
            Expense(2, 30.0, "Bus ticket", "Transportation", Date(), 1),
            Expense(3, 100.0, "Movie tickets", "Entertainment", Date(), 1)
        )
        expenseAdapter.updateExpenses(dummyExpenses)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_expense_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_filter -> {
                // TODO: Show filter dialog
                Toast.makeText(this, "Filter", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_sort -> {
                // TODO: Show sort dialog
                Toast.makeText(this, "Sort", Toast.LENGTH_SHORT).show()
                true
            }
 