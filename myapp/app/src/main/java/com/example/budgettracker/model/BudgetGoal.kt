package com.example.budgettracker.model

data class BudgetGoal(
    val id: Long = 0,
    val category: String,
    val amount: Double,
    val period: String, // "daily", "weekly", "monthly", "yearly"
    val userId: Long
) 