package com.example.budgettracker

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.budgettracker.databinding.ActivityAddExpenseBinding
import com.example.budgettracker.model.Expense
import java.util.Date

class AddExpenseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddExpenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up category dropdown
        val categories = arrayOf("Food", "Transportation", "Entertainment", "Shopping", "Bills", "Other")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories)
        binding.categoryDropdown.setAdapter(adapter)

        // Set up save button
        binding.buttonSave.setOnClickListener {
            saveExpense()
        }
    }

    private fun saveExpense() {
        val amount = binding.editAmount.text.toString()
        val description = binding.editDescription.text.toString()
        val category = binding.categoryDropdown.text.toString()

        if (amount.isEmpty() || description.isEmpty() || category.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val expenseAmount = amount.toDouble()
            val expense = Expense(
                amount = expenseAmount,
                description = description,
                category = category,
                date = Date(),
                userId = 1 // TODO: Replace with actual user ID
            )

            // TODO: Save expense to database
            Toast.makeText(this, "Expense saved successfully", Toast.LENGTH_SHORT).show()
            finish()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
} 