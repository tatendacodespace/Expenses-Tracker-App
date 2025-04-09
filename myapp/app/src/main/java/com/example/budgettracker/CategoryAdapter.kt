package com.example.budgettracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettracker.databinding.ItemCategoryBinding
import com.example.budgettracker.model.Category

class CategoryAdapter(
    private val onEditClick: (Category) -> Unit,
    private val onDeleteClick: (Category) -> Unit
) : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CategoryViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.apply {
                textCategoryName.text = category.name
                imageCategoryIcon.setImageResource(
                    getIconResourceId(category.icon)
                )

                buttonEdit.setOnClickListener {
                    onEditClick(category)
                }

                buttonDelete.setOnClickListener {
                    onDeleteClick(category)
                }
            }
        }

        private fun getIconResourceId(iconName: String): Int {
            return when (iconName) {
                "restaurant" -> R.drawable.ic_restaurant
                "directions_car" -> R.drawable.ic_directions_car
                "movie" -> R.drawable.ic_movie
                "shopping_cart" -> R.drawable.ic_shopping_cart
                "receipt" -> R.drawable.ic_receipt
                else -> R.drawable.ic_category_default
            }
        }
    }

    private class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
} 