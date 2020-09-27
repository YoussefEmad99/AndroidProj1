package com.example.androidproj1.UI

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.R
import com.example.androidproj1.fragments.FragmentA
import com.example.androidproj1.fragments.FragmentB
import com.example.androidproj1.fragments.FragmentC
import com.example.androidproj1.recyclerview.MovieAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.item_list.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchFragment()
    }

    private fun switchFragment() {
        var checker = "A"
        var frag: Fragment = FragmentA()
        val bottomNav: BottomNavigationView = findViewById(R.id.main_bottom_bar)

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.fragmentA -> {
                    if(checker != "A") {
                        frag = FragmentA()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_container, frag)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                        checker = "A"
                    }
                }

                R.id.fragmentB -> {
                    if(checker != "B") {
                        frag = FragmentB()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_container, frag)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                        checker = "B"
                    }
                }

                R.id.fragmentC -> {
                    if (checker != "C") {
                        frag = FragmentC()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_container, frag)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                        checker = "C"
                    }
                }
            }
            true
        }
    }

}



