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
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.R
import com.example.androidproj1.UI.MainViewModel
import com.example.androidproj1.recyclerview.MovieAdapter
import com.example.androidproj1.repository.MovieRepository.requestMovieData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.item_list.view.*


class FragmentA : Fragment()
//    ,MovieViewHolder.onMovieListener
    {

    private var recyclerState: Parcelable? = null
    private var loadingBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_a, container, false)




        return view

        
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieRecycler.addOnItemTouchListener(RecyclerItemClickListener
            (view.context, movieRecycler, object : RecyclerItemClickListener.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                val movieName = view.moviename.text.toString()
                val popularity = view.moviedescription.text.toString()
                val movieDetails = MovieDetails(movieName,popularity)

                val action = FragmentADirections.actionFragmentAToDetailedPage(movieDetails)
                findNavController().navigate(action)


            }

        }))
        //reference to the loading bar
        loadingBar = activity?.loading_bar
        //view model of the current fragment
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer {
            bindMovieData(it)
            movieRecycler.layoutManager?.onRestoreInstanceState(recyclerState)


        })

        viewModel.onError.observe(viewLifecycleOwner, Observer {
            handlingErr(it)
            loadingBar?.visibility = View.GONE
        })

        //load data and show indefinite loading progress bar
        loadingBar?.visibility = View.VISIBLE
        viewModel.loadMovie(::requestMovieData)

        activity?.fab?.setOnClickListener {
            loadingBar?.visibility = View.VISIBLE
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

    private fun bindMovieData(movies: List<UIMovie>) {
        movieRecycler.adapter = MovieAdapter(movies)
        loadingBar?.visibility = View.GONE
    }

    private fun handlingErr(errMsg: String) {
        Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show()
        loadingBar?.visibility = View.GONE
    }

    private fun isLastItemDisplaying(recyclerView: RecyclerView): Boolean {
        if (recyclerView.adapter?.itemCount != 0) {
            val lastVisibleItemPosition = (recyclerView.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION &&
                lastVisibleItemPosition == recyclerView.adapter?.itemCount?.minus(1)
            )
                return true
        }
        return false
    }





}
