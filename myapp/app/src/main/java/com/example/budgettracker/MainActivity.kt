package com.example.budgettracker

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.budgettracker.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up navigation drawer
        drawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener(this)

        // Load default fragment (Dashboard)
        if (savedInstanceState == null) {
            binding.navigationView.setCheckedItem(R.id.nav_dashboard)
            loadFragment(DashboardFragment())
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_dashboard -> {
                loadFragment(DashboardFragment())
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_add_expense -> {
                startActivity(Intent(this, AddExpenseActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_expense_list -> {
                startActivity(Intent(this, ExpenseListActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_categories -> {
                startActivity(Intent(this, CategoriesActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_budget_goals -> {
                startActivity(Intent(this, BudgetGoalsActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_reports -> {
                startActivity(Intent(this, ReportsActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_achievements -> {
                startActivity(Intent(this, AchievementsActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_settings -> {
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_logout -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                return true
            }
            else -> return false
        }
    }

    private fun loadFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, fragment)
            .commit()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
} 