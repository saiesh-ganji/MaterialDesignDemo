package com.demo.materialdesigndemo

import android.graphics.Color.red
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class BottomNavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val homeBadge = navView.getOrCreateBadge(R.id.navigation_home)
        homeBadge.isVisible= true
        val dashBadge = navView.getOrCreateBadge(R.id.navigation_dashboard)
        dashBadge.isVisible= true
        dashBadge.number = 15
        val notiBadge = navView.getOrCreateBadge(R.id.navigation_notifications)
        notiBadge.maxCharacterCount = 2
        notiBadge.backgroundColor = resources.getColor(R.color.red)
        notiBadge.number = 50
    }
}