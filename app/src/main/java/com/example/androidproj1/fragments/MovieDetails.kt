package com.example.androidproj1.fragments

import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class MovieDetails(val name: String , val pop:String , val img:@RawValue ImageView, val description: String, val date: String, val id: Int) : Parcelable {
    var movieName:String = name
    var popularity: String = pop
    var movieImage: ImageView = img
    val description2: String = description
    val date2: String = date
    val id2: Int = id

}