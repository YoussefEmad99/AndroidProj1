package com.example.androidproj1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.R
import com.example.androidproj1.UI.MainViewModel
import com.example.androidproj1.recyclerview.MovieAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a.*

class FragmentA : Fragment() {

    private var loadingBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //reference to the loading bar
        loadingBar = activity?.loading_bar
        //view model of the current fragment
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer {
            bindMovieData(it)
        })

        viewModel.onError.observe(viewLifecycleOwner, Observer {
            handlingErr(it)
        })

        //load data and show indefinite loading progress bar
        viewModel.loadMovie()
        loadingBar?.visibility = View.VISIBLE

        activity?.fab?.setOnClickListener {
            viewModel.loadMovie(true)
            loadingBar?.visibility = View.VISIBLE
        }

    }

    private fun bindMovieData(movies: List<UIMovie>) {
        movieRecycler.adapter = MovieAdapter(movies)
        loadingBar?.visibility = View.GONE
    }

    private fun handlingErr(errMsg: String) {
        Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show()
        loadingBar?.visibility = View.GONE
    }
}