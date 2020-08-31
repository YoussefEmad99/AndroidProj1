package com.example.androidproj1.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproj1.MainActivity
import com.example.androidproj1.R
import kotlinx.android.synthetic.main.layout_item_view.view.*

class MovieAdapter(var items: ArrayList<Movies>, var clickListener: MainActivity): RecyclerView.Adapter<MovieViewHolder>(){
   override fun getItemCount(): Int{
       return items.size
   }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        lateinit var movieViewHolder: MovieViewHolder
        movieViewHolder = MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_view,parent,false))
        return movieViewHolder
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int){
//        holder.movieName?.text = items.get(position).name
//        holder.movieDescription?.text= items.get(position).description
//        holder.movieImage.setImageResource(items.get(position).Image)
        holder.initialize(items.get(position),clickListener)
    }

}
class MovieViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    var movieName = itemView.moviename
    var movieDescription = itemView.moviedescription
    var movieImage =itemView.movieimage

    fun initialize(item: Movies, action:OnMovieItemClickListener){
        movieName.text = item.name
        movieDescription.text = item.description
        movieImage.setImageResource(item.image.toInt()) //ma3rfsh hal hana5od string numeric wala hayeb2a fih kalam fa 3mltha to int le7ad ma3rf

        itemView.setOnClickListener{
            action.onItemClick(item,adapterPosition)
        }

    }
}
interface OnMovieItemClickListener{
    fun onItemClick(item: Movies, position: Int)
}