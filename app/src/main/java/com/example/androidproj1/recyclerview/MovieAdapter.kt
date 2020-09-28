package com.example.androidproj1.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproj1.Models.UI.UIMovie
import com.example.androidproj1.R
import com.example.androidproj1.fragments.FragmentADirections
import com.example.androidproj1.fragments.MovieDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*
import com.example.androidproj1.repository.MovieRepository.addMovieFav
import com.example.androidproj1.repository.MovieRepository.deleteMovieFav
import com.example.androidproj1.repository.MovieRepository.isMovieFav


class MovieAdapter(private var items: List<UIMovie>, private val actionFunction: (MovieDetails) -> NavDirections)
    : RecyclerView.Adapter<MovieViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.initialize(items[position], actionFunction)
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageBaseUrl = "https://image.tmdb.org/t/p/w200"
    //private  var appDatabase: MovieDB =  MovieDB.getDatabase(application)

    private var movieName: TextView = itemView.moviename    //removed because of removing title (new design)
    var movieDescription: TextView = itemView.moviedescription
    var movieImage: ImageView = itemView.movieimage
    var progressBar: ProgressBar = itemView.progressBar
    var favButton: ImageButton = itemView.favButton

    @SuppressLint("SetTextI18n")
    fun initialize(item: UIMovie, actionFunction: (MovieDetails) -> NavDirections) {
        movieName.text = item.title   //removed because of removing title (new design)
        movieDescription.text = "${item.popularity}"

        Picasso.get().load("$imageBaseUrl${item.imgPath}").into(movieImage)
        progressBar.progress = (item.popularity * 10).toInt()

        if (isMovieFav(item.id)) {
            favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

        movieImage.setOnClickListener {
            val action = actionFunction(MovieDetails(item.title, item.popularity.toString()))
            Navigation.findNavController(itemView).navigate(action)
        }

        favButton.setOnClickListener{
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