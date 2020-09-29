package com.example.androidproj1.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.androidproj1.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav: BottomNavigationView = findViewById(R.id.main_bottom_bar)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
//        val navController = navHostFragment.navController
//        NavigationUI.setupWithNavController(bottomNav, navController)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.fragmentA -> {
                    findNavController(R.id.main_container)
                        .navigate(R.id.fragmentA)
                }
                R.id.fragmentB -> {
                    findNavController(R.id.main_container)
                        .navigate(R.id.fragmentB)
                }
                R.id.fragmentC -> {
                    findNavController(R.id.main_container)
                        .navigate(R.id.fragmentC)
                }
            }
            true
        }
    }
}