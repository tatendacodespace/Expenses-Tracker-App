package com.example.budgettracker.model

import java.util.Date

data class Expense(
    val id: Long = 0,
    val amount: Double,
    val description: String,
    val category: String,
    val date: Date,
    val userId: Long
) 