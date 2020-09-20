package com.example.androidproj1.UI

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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




    private fun switchFragment(){

        lateinit var FragmentA: FragmentA
        lateinit var FragmentB: FragmentB
        lateinit var FragmentC: FragmentC
        val bottomNav: BottomNavigationView = findViewById(R.id.main_bottom_bar)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){

                R.id.fragmentA ->{
                    FragmentA = FragmentA()
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, FragmentA)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()

                }

                R.id.fragmentB ->{
                    FragmentB = FragmentB()
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, FragmentB)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()

                }

                R.id.fragmentC ->{
                    FragmentC = FragmentC()
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, FragmentC)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()

                }
            }
            true
        }

    }
}
