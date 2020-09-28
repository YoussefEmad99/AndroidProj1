package com.example.androidproj1.UI

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.R
import com.example.androidproj1.fragments.*
import com.example.androidproj1.recyclerview.MovieAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.item_list.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav: BottomNavigationView = findViewById(R.id.main_bottom_bar)


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


    }}



