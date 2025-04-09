package com.example.budgettracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettracker.databinding.ItemAchievementBinding
import com.example.budgettracker.model.Achievement

class AchievementAdapter : ListAdapter<Achievement, AchievementAdapter.AchievementViewHolder>(AchievementDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementViewHolder {
        val binding = ItemAchievementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AchievementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AchievementViewHolder(
        private val binding: ItemAchievementBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(achievement: Achievement) {
            binding.apply {
                textAchievementName.text = achievement.name
                textAchievementDescription.text = achievement.description
                progressIndicator.progress = achievement.progress

                // Set achievement icon based on completion status
                val iconResourceId = when (achievement.icon) {
                    "ic_achievement_budget" -> R.drawable.ic_achievement_budget
                    "ic_achievement_savings" -> R.drawable.ic_achievement_savings
                    "ic_achievement_categories" -> R.drawable.ic_achievement_categories
                    "ic_achievement_streak" -> R.drawable.ic_achievement_streak
                    else -> R.drawable.ic_achievement_default
                }
                imageAchievementIcon.setImageResource(iconResourceId)

                // Update UI based on completion status
                if (achievement.isCompleted) {
                    root.alpha = 1.0f
                    textAchievementName.setTextColor(root.context.getColor(R.color.achievement_completed))
                } else {
                    root.alpha = 0.7f
                    textAchievementName.setTextColor(root.context.getColor(R.color.achievement_incomplete))
                }
            }
        }
    }

    private class AchievementDiffCallback : DiffUtil.ItemCallback<Achievement>() {
        override fun areItemsTheSame(oldItem: Achievement, newItem: Achievement): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Achievement, newItem: Achievement): Boolean {
            return oldItem == newItem
        }
    }
} 