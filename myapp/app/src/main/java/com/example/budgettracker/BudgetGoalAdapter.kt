package com.example.budgettracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettracker.databinding.ItemBudgetGoalBinding
import com.example.budgettracker.model.BudgetGoal

class BudgetGoalAdapter(
    private val onEditClick: (BudgetGoal) -> Unit,
    private val onDeleteClick: (BudgetGoal) -> Unit
) : ListAdapter<BudgetGoal, BudgetGoalAdapter.BudgetGoalViewHolder>(BudgetGoalDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetGoalViewHolder {
        val binding = ItemBudgetGoalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BudgetGoalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BudgetGoalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BudgetGoalViewHolder(
        private val binding: ItemBudgetGoalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(goal: BudgetGoal) {
            binding.apply {
                textCategory.text = goal.category
                textAmount.text = String.format("$%.2f", goal.amount)
                textPeriod.text = goal.period

                buttonEdit.setOnClickListener {
                    onEditClick(goal)
                }

                buttonDelete.setOnClickListener {
                    onDeleteClick(goal)
                }
            }
        }
    }

    private class BudgetGoalDiffCallback : DiffUtil.ItemCallback<BudgetGoal>() {
        override fun areItemsTheSame(oldItem: BudgetGoal, newItem: BudgetGoal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BudgetGoal, newItem: BudgetGoal): Boolean {
            return oldItem == newItem
        }
    }
} 