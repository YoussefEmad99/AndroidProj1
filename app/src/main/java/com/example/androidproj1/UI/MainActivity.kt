package com.example.androidproj1.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.androidproj1.R
import com.example.androidproj1.fragments.FragmentA
import com.example.androidproj1.fragments.FragmentB
import com.example.androidproj1.fragments.FragmentC
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchFragment()
    }

    private fun switchFragment() {
        var checker = "A"
        var frag: Fragment
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



