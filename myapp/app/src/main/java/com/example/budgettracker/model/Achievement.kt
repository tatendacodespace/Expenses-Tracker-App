package com.example.budgettracker.model

data class Achievement(
    val id: Long = 0,
    val name: String,
    val description: String,
    val icon: String,
    val progress: Int, // 0-100
    val isCompleted: Boolean,
    val userId: Long
) 