package com.example.androidproj1.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproj1.R
import com.example.androidproj1.network.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_item_view.view.*

class MovieAdapter(var items: List<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.initialize(items[position])
    }

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageBaseUrl = "https://image.tmdb.org/t/p/w200"
    var movieName: TextView = itemView.moviename
    var movieDescription: TextView = itemView.moviedescription
    var movieImage: ImageView = itemView.movieimage

    fun initialize(item: Movie) {
        movieName.text = item.movieName
        movieDescription.text = item.popularity.toString()
        Picasso.get().load("$imageBaseUrl${item.imageURL}").into(movieImage)
    }
}