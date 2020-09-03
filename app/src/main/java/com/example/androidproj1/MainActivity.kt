package com.example.androidproj1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidproj1.network.APIResponse
import com.example.androidproj1.recyclerview.MovieAdapter
import com.example.androidproj1.repository.MovieRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieRepository.MovieCallback {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieRecycler.layoutManager = LinearLayoutManager(this)
        movieRecycler.addItemDecoration(DividerItemDecoration(this,1))
        MovieRepository.requestMovieData(this)
    }

    override fun onMovieReady(movies: APIResponse) {
        movieRecycler.adapter = MovieAdapter(movies.movies)
    }

    override fun onMovieLoadingError(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }
}
