package com.example.androidproj1.UI

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.R
import com.example.androidproj1.recyclerview.MovieAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.item_list.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
