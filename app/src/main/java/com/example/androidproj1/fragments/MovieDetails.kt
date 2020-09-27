package com.example.androidproj1.fragments

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetails(val name: String , val pop:String) : Parcelable {
    var movieName:String = name
    var popularity: String = pop
}