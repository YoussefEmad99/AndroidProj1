package com.example.androidproj1.fragments

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.R
import com.example.androidproj1.UI.MainViewModel
import com.example.androidproj1.fragments.FragmentADirections.Companion.actionFragmentAToDetailedPage
import com.example.androidproj1.recyclerview.MovieAdapter
import com.example.androidproj1.repository.MovieRepository.requestMovieData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a.*


class FragmentA : Fragment() {

    private var recyclerState: Parcelable? = null
    private var loadingBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //reference to the loading bar
        loadingBar = activity?.loading_bar
        //view model of the current fragment
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer {
            bindMovieData(it, movieRecycler, loadingBar, ::actionFragmentAToDetailedPage)
            movieRecycler.layoutManager?.onRestoreInstanceState(recyclerState)
        })

        viewModel.onError.observe(viewLifecycleOwner, Observer {
            handlingErr(it, context, loadingBar)
            loadingBar?.visibility = View.GONE
        })

        //load data and show indefinite loading progress bar
        loadingBar?.visibility = View.VISIBLE
        viewModel.loadMovie(::requestMovieData)

        activity?.fab?.setOnClickListener {
            loadingBar?.visibility = View.VISIBLE
            //make Recycler state to null and reset current counter page to 1 so data can be refreshed
            recyclerState = null
            viewModel.resetEntries()
            viewModel.loadMovie(::requestMovieData)
        }

        movieRecycler.setOnScrollChangeListener{ recyclerView, _, _, _, _ ->
            if(recyclerView !is RecyclerView)
                return@setOnScrollChangeListener

            if(isLastItemDisplaying(movieRecycler)){
                recyclerState = movieRecycler.layoutManager?.onSaveInstanceState()
                loadingBar?.visibility = View.VISIBLE
                viewModel.loadMovie(::requestMovieData)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        restoreState(activity?.fab)
    }
}