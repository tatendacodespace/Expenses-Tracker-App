package com.example.budgettracker

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgettracker.databinding.ActivityBudgetGoalsBinding
import com.example.budgettracker.model.BudgetGoal

class BudgetGoalsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBudgetGoalsBinding
    private lateinit var budgetGoalAdapter: BudgetGoalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetGoalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up category dropdown
        val categories = arrayOf("Food", "Transportation", "Entertainment", "Shopping", "Bills", "Other")
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories)
        binding.categoryDropdown.setAdapter(categoryAdapter)

        // Set up period dropdown
        val periods = arrayOf("Daily", "Weekly", "Monthly", "Yearly")
        val periodAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, periods)
        binding.periodDropdown.setAdapter(periodAdapter)

        // Set up RecyclerView
        budgetGoalAdapter = BudgetGoalAdapter(
            onEditClick = { goal -> editBudgetGoal(goal) },
            onDeleteClick = { goal -> deleteBudgetGoal(goal) }
        )

        binding.recyclerViewBudgetGoals.apply {
            layoutManager = LinearLayoutManager(this@BudgetGoalsActivity)
            adapter = budgetGoalAdapter
        }

        // Set up save button
        binding.buttonSaveGoal.setOnClickListener {
            saveBudgetGoal()
        }

        // Load budget goals
        loadBudgetGoals()
    }

    private fun loadBudgetGoals() {
        // TODO: Replace with actual database query
        val dummyGoals = listOf(
            BudgetGoal(1, "Food", 500.0, "Monthly", 1),
            BudgetGoal(2, "Transportation", 200.0, "Monthly", 1),
            BudgetGoal(3, "Entertainment", 300.0, "Monthly", 1)
        )
        budgetGoalAdapter.submitList(dummyGoals)
    }

    private fun saveBudgetGoal() {
        val category = binding.categoryDropdown.text.toString()
        val amount = binding.editAmount.text.toString()
        val period = binding.periodDropdown.text.toString()

        if (category.isEmpty() || amount.isEmpty() || period.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val goalAmount = amount.toDouble()
            val goal = BudgetGoal(
                category = category,
                amount = goalAmount,
                period = period,
                userId = 1 // TODO: Replace with actual user ID
            )

            // TODO: Save goal to database
            Toast.makeText(this, "Budget goal saved successfully", Toast.LENGTH_SHORT).show()
            loadBudgetGoals() // Reload goals
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
        }
    }

    private fun editBudgetGoal(goal: BudgetGoal) {
        // TODO: Show dialog to edit budget goal
        Toast.makeText(this, "Edit Goal: ${goal.category}", Toast.LENGTH_SHORT).show()
    }

    private fun deleteBudgetGoal(goal: BudgetGoal) {
        // TODO: Show confirmation dialog and delete goal
        Toast.makeText(this, "Delete Goal: ${goal.category}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_budget_goals, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_sort -> {
                // TODO: Show sort dialog
                Toast.makeText(this, "Sort Goals", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
} 