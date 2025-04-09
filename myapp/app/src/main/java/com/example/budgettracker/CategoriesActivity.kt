package com.example.budgettracker

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgettracker.databinding.ActivityCategoriesBinding
import com.example.budgettracker.model.Category

class CategoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up RecyclerView
        categoryAdapter = CategoryAdapter(
            onEditClick = { category -> editCategory(category) },
            onDeleteClick = { category -> deleteCategory(category) }
        )

        binding.recyclerViewCategories.apply {
            layoutManager = LinearLayoutManager(this@CategoriesActivity)
            adapter = categoryAdapter
        }

        // Set up FAB
        binding.fabAddCategory.setOnClickListener {
            showAddCategoryDialog()
        }

        // Load categories
        loadCategories()
    }

    private fun loadCategories() {
        // TODO: Replace with actual database query
        val dummyCategories = listOf(
            Category(1, "Food", "restaurant", 1),
            Category(2, "Transportation", "directions_car", 1),
            Category(3, "Entertainment", "movie", 1),
            Category(4, "Shopping", "shopping_cart", 1),
            Category(5, "Bills", "receipt", 1)
        )
        categoryAdapter.submitList(dummyCategories)
    }

    private fun showAddCategoryDialog() {
        // TODO: Show dialog to add new category
        Toast.makeText(this, "Add Category", Toast.LENGTH_SHORT).show()
    }

    private fun editCategory(category: Category) {
        // TODO: Show dialog to edit category
        Toast.makeText(this, "Edit Category: ${category.name}", Toast.LENGTH_SHORT).show()
    }

    private fun deleteCategory(category: Category) {
        // TODO: Show confirmation dialog and delete category
        Toast.makeText(this, "Delete Category: ${category.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_categories, menu)
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
                Toast.makeText(this, "Sort Categories", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
} 