package com.example.budgettracker.model

data class User(
    val id: Long = 0,
    val username: String,
    val email: String,
    val password: String // In a real app, this should be hashed
) 