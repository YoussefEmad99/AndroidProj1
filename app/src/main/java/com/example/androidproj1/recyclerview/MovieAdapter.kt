package com.example.androidproj1.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproj1.DataBase.MovieDB
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.view.*
import com.example.androidproj1.fragments.FragmentA
import com.example.androidproj1.network.Movie
import com.example.androidproj1.repository.MovieRepository
import com.example.androidproj1.repository.MovieRepository.addMovieFav
import com.example.androidproj1.repository.MovieRepository.appDatabase
import com.example.androidproj1.repository.MovieRepository.deleteMovieFav
import com.example.androidproj1.repository.MovieRepository.getFavouriteMovieList
import com.example.androidproj1.repository.MovieRepository.isMovieFav


class MovieAdapter(private var items: List<UIMovie>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.initialize(items[position])
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageBaseUrl = "https://image.tmdb.org/t/p/w200"
    //private  var appDatabase: MovieDB = MovieDB.getDatabase(application)

    private var movieName: TextView = itemView.moviename    //removed because of removing title (new design)
    var movieDescription: TextView = itemView.moviedescription
    var movieImage: ImageView = itemView.movieimage
    var progressBar: ProgressBar = itemView.progressBar
    var favButton: ImageButton = itemView.favButton


    @SuppressLint("SetTextI18n")
    fun initialize(item: UIMovie) {
        movieName.text = item.title   //removed because of removing title (new design)
        movieDescription.text = "${item.popularity}"

        Picasso.get().load("$imageBaseUrl${item.imgPath}").into(movieImage)
        progressBar.progress = (item.popularity * 10).toInt()

        if (isMovieFav(item.id)) {
            favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

        favButton.setOnClickListener{v: View ->
            if (!isMovieFav(item.id)){
                favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                addMovieFav(item.id)
                Toast.makeText(itemView.context, "Added ${movieName.text} to favourites",Toast.LENGTH_SHORT).show()
            }
            else{
                favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                deleteMovieFav(item.id)
                Toast.makeText(itemView.context, "Removed ${movieName.text} from favourites",Toast.LENGTH_SHORT).show()
            }
        }
        //view.movieimage.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_fragmentA_to_detailedPage)}
    }
}