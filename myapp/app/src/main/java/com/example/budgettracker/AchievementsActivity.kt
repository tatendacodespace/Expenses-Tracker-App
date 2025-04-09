package com.example.budgettracker

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.budgettracker.databinding.ActivityAchievementsBinding
import com.example.budgettracker.model.Achievement

class AchievementsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAchievementsBinding
    private lateinit var achievementAdapter: AchievementAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAchievementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up RecyclerView
        achievementAdapter = AchievementAdapter()

        binding.recyclerViewAchievements.apply {
            layoutManager = GridLayoutManager(this@AchievementsActivity, 2)
            adapter = achievementAdapter
        }

        // Load achievements
        loadAchievements()
    }

    private fun loadAchievements() {
        // TODO: Replace with actual database query
        val dummyAchievements = listOf(
            Achievement(
                id = 1,
                name = "Budget Master",
                description = "Stay within budget for 3 months",
                icon = "ic_achievement_budget",
                progress = 75,
                isCompleted = false,
                userId = 1
            ),
            Achievement(
                id = 2,
                name = "Saver Extraordinaire",
                description = "Save 20% of income for 6 months",
                icon = "ic_achievement_savings",
                progress = 40,
                isCompleted = false,
                userId = 1
            ),
            Achievement(
                id = 3,
                name = "Category King",
                description = "Use all expense categories",
                icon = "ic_achievement_categories",
                progress = 100,
                isCompleted = true,
                userId = 1
            ),
            Achievement(
                id = 4,
                name = "Early Bird",
                description = "Log expenses for 30 days straight",
                icon = "ic_achievement_streak",
                progress = 60,
                isCompleted = false,
                userId = 1
            )
        )

        // Update progress indicator
        val completedCount = dummyAchievements.count { it.isCompleted }
        binding.completedCountText.text = "$completedCount/${dummyAchievements.size}"
        binding.achievementProgressIndicator.progress = 
            (completedCount * 100) / dummyAchievements.size

        achievementAdapter.submitList(dummyAchievements)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_achievements, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_filter -> {
                // TODO: Show filter dialog
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
} 