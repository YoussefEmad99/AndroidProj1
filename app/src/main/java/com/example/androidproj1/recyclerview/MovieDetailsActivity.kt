package com.example.androidproj1.recyclerview

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidproj1.R
import kotlinx.android.synthetic.main.activity_movie_details.*
import androidx.databinding.DataBindingUtil
import com.example.androidproj1.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {
    lateinit var binding : ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        movie_name.text = getIntent().getStringExtra("MOVIENAME")
        movie_description.text = getIntent().getStringExtra("MOVIEDESC")
        image_movie.setImageResource(getIntent().getStringExtra("CARLOGO")!!.toInt())


    }
}